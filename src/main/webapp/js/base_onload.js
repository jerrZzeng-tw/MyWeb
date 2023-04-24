//(function ($) {
// 系統標題和選單的高度
var bannerHeight = 96;
// 系統選單列的高度
var menuHeight = 26;
// 訊息區的高度
var sysMsgHeight = 30;
// Offset
var contentOffset = 4;
// 游標停在輸入欄位時的背景顏色
var inputFocusBackgroundColor = "#DDF4FF";
// 游標離開輸入欄位時的背景顏色
var inputBlurBackgroundColor = "rgb(255, 255, 255, 0)";
// 游標離開輸入欄位時的背景顏色 for 驗證錯誤的欄位
var inputErrorBackgroundColor = "#FFEBEB";

// 防止 報表 或 下載 頁面 Double Submit
// [
var _btnLockCookieName = "WebAppBtnLockCookie";
var _btnLockCheckInterval = 1000;
var _previousBtnLockCookieValue = null;
var _disableZoneId = "screenDisabledZone";
var _messageZoneId = "loadingMessageZone";
// ]

// 改變視窗大小時重新計算各區塊高度
function resizeContent() {
    var myWidth = 0,
        myHeight = 0;

    if (typeof (window.innerWidth) == 'number') {
        //Non-IE
        myWidth = window.innerWidth;
        myHeight = window.innerHeight;
    } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
        //IE 6+ in 'standards compliant mode'
        myWidth = document.documentElement.clientWidth;
        myHeight = document.documentElement.clientHeight;
    } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
        //IE 4 compatible
        myWidth = document.body.clientWidth;
        myHeight = document.body.clientHeight;
    }

    if (myHeight > (bannerHeight + menuHeight + sysMsgHeight + contentOffset)) {
        if ($("#main").length > 0) {
            $("#main").height(myHeight - (bannerHeight + menuHeight + sysMsgHeight + contentOffset));
        }

        if ($("#main2").length > 0) {
            $("#main2").height(myHeight - (bannerHeight + menuHeight + sysMsgHeight + contentOffset));
        }

        if ($("#footer").length > 0) {
            $("#footer").height(sysMsgHeight);
        }
    }
}

// 輸入欄位相關處理
// Begin ... [

function inputFieldInit() {
    //$("input:text").on("focus", focusBackgroundColor).on("blur", blurBackgroundColor).on("keydown", disableEnterKey);
    $(":input:not(:image, :button, :submit, :reset)").on("focus", focusBackgroundColor).on("blur", blurBackgroundColor).on("keydown", disableEnterKey);
    $(document).on("keydown", disableEnterKey);
}

// 進入頁面後, 游標停在第一筆輸入欄位
function inputFieldFocus() {
    var inputFieldList = $(":input:not(:image, :button, :submit, :reset)").not(":hidden").not("[disabled]").not("[readonly]").not(function () {
        return ($(this).css("visibility").toLowerCase() == "hidden") ? true : false;
    });

    var errorInputList = $(":input[class~='textinputError']:not(:image, :button, :submit, :reset), :input[class='inputControlError']:not(:image, :button, :submit, :reset)").not(":hidden").not("[disabled]").not("[readonly]").not(function () {
        return ($(this).css("visibility").toLowerCase() == "hidden") ? true : false;
    });

    if (inputFieldList.length > 0) {
        var inputObj = null;

        if (errorInputList != null && errorInputList.length > 0) {
            inputObj = errorInputList.first();
            if (inputObj.is(":text") || inputObj.is("textarea"))
                inputObj.css("background", inputFocusBackgroundColor);
            inputObj.focus();
            //inputObj.select();
        } else {
            inputObj = inputFieldList.first();
            if (inputObj.is(":text") || inputObj.is("textarea"))
                inputObj.css("background", inputFocusBackgroundColor);
            inputObj.focus();
        }
    }
}

// 游標停在輸入欄位時改變背景顏色
function focusBackgroundColor(event) {
    var elementObj = $(event.target);

    if (!$(elementObj).is('[readonly]')) {
        /*
        if (!elementObj.hasClass("textinput") && !elementObj.hasClass("textinputError"))
            return;
        */
        elementObj.css("background", inputFocusBackgroundColor);
    }
}

// 游標離開輸入欄位時重置背景色
function blurBackgroundColor(event) {
    var elementObj = $(event.target);
    if (!$(elementObj).is('[readonly]')) {
        /*
        if (!elementObj.hasClass("textinput") && !elementObj.hasClass("textinputError"))
            return;
        */
        if (elementObj.hasClass("textinputError"))
            elementObj.css("background", inputErrorBackgroundColor);
        else
            elementObj.css("background", inputBlurBackgroundColor);
    }
}

function disableEnterKey(e) {
    var key;
    if (window.event)
        key = window.event.keyCode; //IE
    else
        key = e.which; //firefox

    if (key == 13) {
        if (window.event)
            event.keyCode = 35;

        var bodyElement = document.getElementsByTagName("body");
        if (bodyElement != null) {
            var relObj = bodyElement[0].getAttribute('rel');
            if (relObj != null) {
                // Auto Submit 的頁面
                var targetObj = e.target;

                if (String(targetObj.tagName).toLowerCase().match('input')) {
                    var inputType = String(targetObj.getAttribute('type'));
                    if (inputType.toLowerCase().match('submit') || inputType.toLowerCase().match('reset') || inputType.toLowerCase().match('button')) {
                        event.keyCode = 13;
                        return true;
                    }
                }

                document.getElementById(relObj).click();
            } else {
                // No Auto Submit 的頁面
                var targetObj = e.target;

                if (String(targetObj.tagName).toLowerCase().match('input')) {
                    var inputType = String(targetObj.getAttribute('type'));

                    if (inputType.toLowerCase().match('submit') || inputType.toLowerCase().match('reset') || inputType.toLowerCase().match('button')) {
                        event.keyCode = 13;
                        return true;
                    }
                }
            }
        }

        return false;
    } else {
        return true;
    }

}

// 文字輸入欄位打滿自動移到下一個欄位
// 注意: 各欄位需定義 tabindex
function autoTab() {
    $("input[type=text]").keyup(function (event) {

        if (event.keyCode != 8 && event.keyCode != 9 && event.keyCode != 12 && event.keyCode != 13 &&
            event.keyCode != 16 && event.keyCode != 17 && event.keyCode != 18 && event.keyCode != 20 &&
            event.keyCode != 27 && event.keyCode != 32 && event.keyCode != 33 && event.keyCode != 34 &&
            event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 38 &&
            event.keyCode != 39 && event.keyCode != 40 && event.keyCode != 45 && event.keyCode != 46) {

            var elementObj = $(event.target);

            if (elementObj.prop("maxlength") > 0) {
                if (elementObj.val().length >= elementObj.prop("maxlength")) {

                    var thisIndex = elementObj.prop("tabindex");
                    var minIndex = 99999999;
                    var nextIndex = 99999999;

                    var inputFieldList = $("input:text").not(":hidden").not("[disabled]").not("[readonly]").not(function () {
                        return ($(this).css("visibility").toLowerCase() == "hidden") ? true : false;
                    });

                    inputFieldList.each(function (index) {
                        if ($(this).prop("tabindex") < minIndex && !$(this).prop("disabled")) {
                            minIndex = $(this).prop("tabindex");
                        }
                        if ($(this).prop("tabindex") < nextIndex && $(this).prop("tabindex") > thisIndex && !$(this).prop("disabled")) {
                            nextIndex = $(this).prop("tabindex");
                        }
                    });


                    // if (nextIndex == 99999999)
                    //     $("input[type='text'][tabindex=" + minIndex +  "]")[0].focus();
                    // else
                    if ($("input[type='text'][tabindex=" + nextIndex + "]").length > 0)
                        $("input[type='text'][tabindex=" + nextIndex + "]")[0].focus();
                }
            }
        }
    });
}

// ] ... End
// 輸入欄位相關處理

function beforeUnload() {
    $("#systemMessage").text(""); // 清空訊息區
    createBlockUiZone();
}

function createBlockUiZone() {
    var bodyObj = document.getElementsByTagName("body").item(0);

    var disabledZone = document.getElementById(_disableZoneId);

    if (!disabledZone) {
        disabledZone = document.createElement('div');
        disabledZone.setAttribute('id', _disableZoneId);

        disabledZone.style.position = "fixed";
        disabledZone.style.zIndex = "999999";
        disabledZone.style.left = "0px";
        disabledZone.style.top = "0px";
        disabledZone.style.width = "100%";
        disabledZone.style.height = "100%";
        disabledZone.style.backgroundImage = "url('images/blockUi.gif')";

        var messageZone = document.createElement('div');
        messageZone.setAttribute('id', _messageZoneId);
        messageZone.style.position = "absolute";
        messageZone.style.bottom = "0px";
        messageZone.style.right = "0px";
        messageZone.style.background = "red";
        messageZone.style.color = "white";
        messageZone.style.fontFamily = "新細明體,細明體,Arial,Helvetica,sans-serif";
        messageZone.style.padding = "4px";
        messageZone.style.border = "1px solid #000000";
        var text = document.createTextNode("處理中, 請稍候...");
        messageZone.appendChild(text);

        disabledZone.appendChild(messageZone);
        bodyObj.appendChild(disabledZone);

        disabledZone.style.display = 'block';
    } else {
        document.getElementById(_messageZoneId).innerHTML = "處理中, 請稍候...";
        disabledZone.style.display = 'block';
        disabledZone.style.visibility = 'visible';
    }
}

// 防止 報表 或 下載 頁面 Double Submit
// Begin ... [

/*
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }

    return "";
}

function checkCookie(cookieName) {
    var cookieValue = getCookie(cookieName);
    if (cookieValue != "") {
        return true;
    } else {
        return false;
    }
}

function setCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue;
}
*/

function unlockAllButtons() {
    var disabledZone = document.getElementById(_disableZoneId);
    if (disabledZone) {
        disabledZone.style.display = 'none';
    }
}

/*
function intervalHandler() {
    var newCookieValue = getCookie(_btnLockCookieName);
    if (newCookieValue != _previousBtnLockCookieValue) {
        _previousBtnLockCookieValue = newCookieValue;
        unlockAllButtons();
    }
}

function checkBtnLockStatus() {
    if (checkCookie(_btnLockCookieName)) {
        _previousBtnLockCookieValue = getCookie(_btnLockCookieName);
    } else {
        var defaultCookieValue = "00000000000000";
        setCookie(_btnLockCookieName, defaultCookieValue);
        _previousBtnLockCookieValue = defaultCookieValue;
    }

    var myVar = setInterval('intervalHandler()', _btnLockCheckInterval);
}
*/

// ] ... End
// 防止 報表 或 下載 頁面 Double Submit

// 分頁多開相關防範功能
// Begin ... [

/*
var APP_WINDOW_NAME_KEY = "app_window_name_key";
var APP_WINDOW_NAME = "app_window_name";

function setWindowName() {
    if (window.name == '') {
        var bExist = checkCookie(APP_WINDOW_NAME_KEY);
        if (!bExist) {
            window.name = APP_WINDOW_NAME;
            setCookie(APP_WINDOW_NAME_KEY, APP_WINDOW_NAME);
        }
    }
}

function mutipleOpenChecking() {
    if (window.name == '') {
        var bExist = checkCookie(APP_WINDOW_NAME_KEY);
        if (bExist) {
            showAlertCallback("本功能不支援多開，此分頁將關閉，請回最原始分頁執行此功能。", mutipleOpenCloseWindow);
        }
    }
}

function mutipleOpenCloseWindow() {
    createBlockUiZone();
    window.opener = null;
    window.close();
}
*/

// ] ... End
// 分頁多開相關防範功能


$(function () {
    $(window).on("resize", resizeContent);
    $(window).on("load", resizeContent);
    // $(window).on("load", inputFieldFocus);
    // $(window).on("load", inputFieldInit);
    // $(window).on("load", autoTab);
    // $(window).on("load", setWindowName); // 不再使用 Cookie 故此功能作廢
    // $(window).on("load", checkBtnLockStatus); // 不再使用 Cookie 故此功能作廢
    $(window).on("beforeunload", beforeUnload);

    inputFieldInit();
    inputFieldFocus();

    if ($(".exportlinks").length > 0) {
        $(".exportlinks").click(function (event) {
            $(window).off("beforeunload", beforeUnload)
        });
    }

    if ($(":input(:button, :submit, :reset)").length > 0) {
        $(":input(:button, :submit, :reset)").click(function (event) {
            $(window).on("beforeunload", beforeUnload);
        });
    }
});

//}(jQuery));
