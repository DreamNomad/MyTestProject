
(function(window, undefined) {
  window.Asc.plugin.init = function(initData) {
    var me = this
    
    /*
    $('#button').click(function() {
      // 回调
      // 第二个参数是设置触发插件后不关闭窗口    
      me.callCommand(function() {
        try {
          // 获取文档对象
          // var oDocument = Api.GetDocument()
          // 生成一个新的段落对象
          // var oParagraph = Api.CreateParagraph()
          // 往段落里面添加一个字符串文本
          // oParagraph.AddText('测试')
          // 向光标处添加
          // oDocument.InsertContent([oParagraph])
          window.Asc.plugin.executeMethod("PasteHtml", ["<p><b>OLE对象的插件方法</b></p><ul><li>AddOleObject</li><li>EditOleObject</li></ul>"]);
        } catch (error) {
          console.error(error)
        }
      }, false, true, function () {
        console.log('ok')
      })
    })*/

    jQuery.fn.longsheng = function() {
			window.Asc.plugin.executeMethod("PasteHtml",[jQuery("#my-table").html()]);
		};

    // 在插件 iframe 之外释放鼠标按钮时调用的函数
    window.Asc.plugin.onExternalMouseUp = function() {
      var event = document.createEvent('MouseEvents')
      event.initMouseEvent('mouseup', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null)
      document.dispatchEvent(event)
    }

    window.Asc.plugin.button = function(id) {
      // 被中断或关闭窗口
      if (id === -1) {
        this.executeCommand('close', '')
      }
	  }
  }
})(window, undefined)