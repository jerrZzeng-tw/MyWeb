<%@ page language="java" pageEncoding="UTF-8" %>
<div id="msgarea" class="infoarea TframeDiv" style="width:100%;display:flex">
    <div class="footer">
        <div class="ifon infotitle"><i class="fas fa-caret-down"></i><b>【訊息區】</b></div>
        <div class="prog infoword"><func:systemMessage/></div>
    </div>
</div>
<script>
    $(function () {
        showAlert('<func:systemAlertMessage />');
    });

    $(function () {
        showAlert('<func:validationMessage />');
    });

    $(function () {
        $(".footer").click(function () {
            $(".infoarea").toggleClass("newheight");
        });

        //查詢結果，核取方塊選取後整列顯示綠色
        $(".checkedgreen").click(function () {
            if ($(this).is(":checked"))
                $(this).parents('tr').addClass("myStrong");
            else
                $(this).parents('tr').removeClass("myStrong");

        });

        //按一下上方核取方塊全選
        $("#selectAll").click(function () {
            if ($("#selectAll").prop("checked")) {//如果全選按鈕有被選擇的話（被選擇是true）
                $(".checkedgreen").prop("checked", true);//把所有的核取方框的property都變成勾選
                $(".checkedgreen").parents('tr').addClass("myStrong");
            } else {
                $(".checkedgreen").prop("checked", false);//把所有的核取方框的property都取消勾選
                $(".checkedgreen").parents('tr').removeClass("myStrong");
            }
        });
        // 訊息區浮動
        let mesg = $('div .prog.infoword').text().trim();
        if (mesg.length > 0) {
            $(".footer").click();
        }
    });


</script>

