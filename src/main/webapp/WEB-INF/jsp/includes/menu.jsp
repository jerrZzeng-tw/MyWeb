<%@ page language="java" pageEncoding="UTF-8" %>

<%-- 動態選單 --%>
<div class="sidenav">
    <div class="tree">
        <ul id="menu" class="root">

        </ul>
    </div>
</div>

<div class="sidenav-button"><i class="fa fa-angle-right"></i></div>
<script>

    <%-- 動態選單 --%>
    <%-- 注意： --%>
    <%-- 由於 `js.jsp` 已改在 `footer.jsp` include， --%>
    <%-- 若欲使用動態選單，請將 `js.jsp` 改為由 `css.jsp` include --%>
    <%--
    --%>
    var _baseUrl = "<c:url value='/' />";
    _baseUrl = _baseUrl.substring(0, _baseUrl.length - 1);
    var _menuData = javaEncodeStringToJSON('<c:out value="${frameworkUserData.menuJson}" />');
    <%-- 動態選單 --%>

    $(document).ready(function () {

        var getMenuItem = function (itemData) {
            var item = null;

            if (itemData.levelNo == "1") {
                if (itemData.sub) {
                    item = $("<li>").append(
                        $("<span class='caret'>").text(itemData.functionName)
                    );
                    var tempItem = $("<ul>", {
                        class: "nested"
                    });
                    $.each(itemData.sub, function () {
                        tempItem.append(getSubMenuItem(this));
                    });
                    item.append(tempItem);
                } else {
                    item = $("<li>").append(
                        $("<a>", {
                            href: _baseUrl + itemData.url,
                            text: itemData.functionName
                        })
                    );
                }
            }

            return item;
        };
        var getSubMenuItem = function (itemData) {
            var item = null;
            var subList = null;
            item = $("<li>").append(
                $("<a>", {
                    href: _baseUrl + itemData.url,
                    text: itemData.functionName
                })
            );
            if (itemData.sub) {
                subList = $("<li>")
                    .append(
                        $("<span class='caret'>").text(itemData.functionName)
                    )
                var tempItem = $("<ul>", {
                    class: "nested"
                });
                $.each(itemData.sub, function () {
                    tempItem.append(getSubMenuItem(this));
                });
                subList.append(tempItem);
            } else {
                return item;
            }
            return subList;
        }

        var $menu = $("#menu");

        $.each(_menuData.menuItemList, function () {
            $menu.append(
                getMenuItem(this)
            );

        });


        // menu open close function
        $(".sidenav .tree").on("click", ".caret", function () {
            $(this)
                .toggleClass("caret-down")
                .siblings(".nested")
                .toggleClass("active");
        });
        $(".sidenav-button").click(function () {
            $(".sidenav").animate({width: "toggle"}, 100);
        });

        // 頁面功能保持展開
        var entryPath = '<c:url value="${requestScope['javax.servlet.forward.servlet_path']}" />';
        if (entryPath.indexOf(".action") > -1) {
            if (entryPath.indexOf("_") > -1) {
                entryPath = entryPath.substring(0, entryPath.lastIndexOf("_") + 1);
            }
            const menus = $("#menu a[href^='" + entryPath + "']").parents('li');
            menus.children(".caret:not(.caret-down)").click();
            const names = $.map(menus, function (item) {
                return $(item).children("a,span").text();
            });
            $('#menuPath').append($.map(names, function (item) {
                return '<li class=\"breadcrumb-item2\">' + item + '</li>';
            }).reverse().join(''));
            if (names.length && !$('#pageTitle').text()) {
                $('#pageTitle').text(names[0]);
            }
        }
    });

</script>



