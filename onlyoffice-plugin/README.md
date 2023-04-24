# config.json参数说明

```json
{
  "guid": "asc.{11700c35-1fdb-4e37-9edb-b31637139601}",
  "variations": [
    {
      "url": "index.html",
      "EditorsSupport": ["word", "cell", "slide"],
      "isSystem": true,
      "initDataType": "none",
      "initData": "",
      "buttons": [],
      "events" : [
        "onClick"
      ]
    }
  ]
}
```

- guid：插件的唯一值, 可自行更改, 但格式必须是asc.{}
- url: 在本插件中该html的作用主要是用来加载js
- EditorsSupport：声明该插件在word、excel、ppt可用
- isSystem：设置该值为true后在菜单栏中不显示该插件, 后台自动运行该插件
- events：声明事件

其他参数请看官方文档：https://api.onlyoffice.com/plugin/config

# 其他说明

plugins.js一般在onlyoffice容器内的/var/www/onlyoffice/documentserver/sdkjs-plugins/v1目录下, 我这里直接是直接从里边拿过来的

main.js就是插件功能的逻辑了

# 使用插件

需要保证config.json、index.html、main.js、plugins.js这四个文件在同一个文件夹当中, 然后将文件夹放入到容器内的/var/www/onlyoffice/documentserver/sdkjs-plugins目录下, 重启下容器即可

调用方法插入文本：

```javascript
docEditor.serviceCommand ('PasteText', data);
```

调用方法插入html：

```javascript
docEditor.serviceCommand ('PasteHtml', data);
```

更多api请参考官方文档：

https://api.onlyoffice.com/plugin/executemethod/common