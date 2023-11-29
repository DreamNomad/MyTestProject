@echo off

:: 切换到当前目录
cd /d "%~dp0"

:: 启用延迟扩展变量
setlocal enabledelayedexpansion

:: 设置命令行代码页为UTF-8，以支持中文字符
chcp 65001

:: 设置JDK的安装路径，确保替换成您的实际路径，路径中的空格要用双引号括起来
set "JDK_HOME=%JAVA_HOME%"

:: 设置输出的JRE文件夹名称，也用双引号括起来
set "JRE_FOLDER=jre"

:: 询问用户是生成标准JRE还是基于JAR的JRE
echo.
echo 请选择生成JRE的类型：
echo [1] 标准JRE(默认)
echo [2] 基于JAR的JRE(目前只支持spring-boot-maven-plugin打包的jar)
echo.
set /p "jreType=请输入选项（1 或 2）: "

if "%jreType%"=="2" goto jarBasedJRE

:: 标准JRE生成流程
goto standardJRE

set "modules="

:jarBasedJRE
:: 列出当前文件夹下的所有JAR文件并编号
echo.
echo 当前文件夹下的JAR文件列表：
set "counter=1"
for %%i in (*.jar) do (
    echo [!counter!] %%i
    set /a "counter+=1"
)

:: 提示用户选择一个JAR文件
echo.
set /p "selection=请选择JAR（输入序号）: "

:: 遍历文件夹以获取用户选择的JAR文件
set "selectedJar="
set "counter=1"
for %%i in (*.jar) do (
    if !counter! equ %selection% (
        set "selectedJar=%%i"
        goto :found
    )
    set /a "counter+=1"
)
:found

:: 检查用户选择的JAR文件是否存在
if not defined selectedJar (
    echo 输入的序号无效或选择的JAR文件不存在。
    pause
    exit /b 1
)


if exist "temp" (
    rmdir /s /q "temp"
)
mkdir "temp"
:: 解压用户选择的JAR文件
echo 解压 !selectedJar!...
cd temp&&jar xf "../!selectedJar!"&&cd ..

:: 提取jdk版本号
set "jdk_version="
for /f "tokens=3" %%i in ('java -version 2^>^&1 ^| findstr /i "openjdk version"') do (
    set "jdk_version=%%i"
    goto :check_version
)

:check_version
for /f "tokens=1 delims=." %%j in (!jdk_version!) do (
    set "jdk_version=%%j" 
)

:: 收集BOOT-INF/lib目录下JAR文件的模块
if exist "temp\BOOT-INF\lib" (
    for %%i in (temp\BOOT-INF\lib\*.jar) do (
        jdeps --multi-release 17 -s "%%i" >nul 2>&1
        if !errorlevel! equ 0 (
            for /f "tokens=3" %%a in ('jdeps --multi-release !jdk_version! -s "%%i"') do (
                echo %%a | findstr /R "^java\. ^jdk\." >nul
                if !errorlevel! equ 0 (
                    set "mod=%%a"
                    call :addModule !mod!
                )
            )
        )
    )
) else (
    rmdir /s /q "temp"
    echo.
    echo 你选择的JAR中未包含 "BOOT-INF\lib" 文件夹。按任意键退出...
    pause >nul
    exit /b 1
)

rmdir /s /q "temp"

goto :standardJRE

:standardJRE
:: 标准JRE生成流程
:: 收集必要的模块
for /f "tokens=1 delims=@" %%i in ('java --list-modules') do (
    set module=%%i
    if "!module:~0,5!"=="java." (
        call :addModule !module!
    )
)
goto :createJRE

:addModule
set "newModule=%~1"
if "!modules!" == "" (
    set "modules=%newModule%"
) else (
    echo "!modules!" | findstr /C:"%newModule%" >nul
    if !errorlevel! neq 0 (
        echo 添加：%newModule%模块
        set "modules=!modules!,%newModule%"
    )
)
goto :eof

:createJRE
echo.
echo 已添加的模块：!modules!
echo.
:: 检查是否存在旧的JRE文件夹，如果存在就删除
if exist "%JRE_FOLDER%" (
    echo 删除旧的JRE文件夹...
    rmdir /s /q "%JRE_FOLDER%"
)
:: 使用jlink根据收集的模块生成新的JRE
"%JDK_HOME%\bin\jlink" --compress=2 --no-header-files --no-man-pages --module-path "%JDK_HOME%\jmods" --add-modules !modules! --output "%JRE_FOLDER%"

:: 检查新JRE是否生成成功
if !errorlevel! neq 0 (
    echo 生成新JRE时出错。
    pause
    exit /b 1
)

:: 输出成功消息
echo 新JRE文件夹生成成功。

:: 是否需要运行测试
if "%jreType%"=="2" (
    echo.
    set /p "runTest=是否需要运行测试？(y/n): "
    if /i "!runTest!"=="y" (
        echo 运行测试...
        "%JRE_FOLDER%\bin\java.exe" -jar "!selectedJar!"
    )
)

:: 等待用户按键后才关闭窗口
pause

:: 关闭延迟扩展变量
endlocal
