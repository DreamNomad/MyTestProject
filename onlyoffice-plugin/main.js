(function(window, undefined) {

  var AscPlugin = window.Asc.plugin;

  var executeMethod = function(method, arg) {
    return window.Asc.plugin.executeMethod(method, arg);
  };

  AscPlugin.init = function(initData) {
    // 避免绑定多次
    if(!window['boundInternalcommand']){
      // 该事件监听似乎在7.x版本后就失效了 我还没尝试过
      window.parent.Common.Gateway.on('internalcommand', function(args) {
        // 如果需要自定义插件的功能, 只需要在这个方法里接受参数写逻辑就行
        var data = args.data;
        var command = args.command;
        (Array.isArray(data) ? data : [data]).forEach(function(arg, index) {
          // 6.x版本中的api不支持批量的操作, 只能单个插入, 这里需要一个定时器才能做到批量的插入, 否则会报错或者什么都不插入
          setTimeout(function() {
            executeMethod(command, [arg]);
          }, 100 * index);
        });
      });
      window['boundInternalcommand'] = true;
    }

    // 在插件弹出窗外释放鼠标时触发
    AscPlugin.onExternalMouseUp = function() {
      var event = document.createEvent('MouseEvents');
      event.initMouseEvent('mouseup', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
      document.dispatchEvent(event);
    };

    AscPlugin.button = function(id) {
      // 关闭插件弹出窗触发
      if (id === -1) {
        this.executeCommand('close', '');
      }
	  };
    
  };

})(window, undefined);
