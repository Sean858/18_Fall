    /* 调用示例 */
    // 窗口全屏
    $('.win-fullscreen').on('click', function() {
        requestFullScreen(document.documentElement);
    });

    // div全屏
    $('.div-fullscreen').on('click', function() {
        requestFullScreen($('.div')[0]);
    });

    // img全屏
    $('.img-fullscreen').on('click', function() {
        requestFullScreen($('.img')[0]);
    });

    // 退出全屏
    $('.exit-fullscreen').on('click', function() {
        exitFull();
    });

    // 窗口状态改变事件
    fullscreenchange(document, function(isFull) {
        console.log(isFull);
    });

    /* 封装 */
    // 窗口状态改变
    function fullscreenchange(el, callback) {
        el.addEventListener("fullscreenchange", function () { 
            callback && callback(document.fullscreen);
        }); 
        el.addEventListener("webkitfullscreenchange", function () { 
            callback && callback(document.webkitIsFullScreen);
        }); 
        el.addEventListener("mozfullscreenchange", function () { 
            callback && callback(document.mozFullScreen);
        }); 
        el.addEventListener("msfullscreenchange", function () { 
            callback && callback(document.msFullscreenElement);
        });
    }

    // 全屏
    function requestFullScreen(element) {
        var requestMethod = element.requestFullScreen || //W3C
        element.webkitRequestFullScreen ||    //Chrome等
        element.mozRequestFullScreen || //FireFox
        element.msRequestFullScreen; //IE11
        if (requestMethod) {
            requestMethod.call(element);
        }else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        }
    }

    //退出全屏
    function exitFull() {
        var exitMethod = document.exitFullscreen || //W3C
        document.mozCancelFullScreen ||    //Chrome等
        document.webkitExitFullscreen || //FireFox
        document.webkitExitFullscreen; //IE11
        if (exitMethod) {
            exitMethod.call(document);
        }else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        }
    }