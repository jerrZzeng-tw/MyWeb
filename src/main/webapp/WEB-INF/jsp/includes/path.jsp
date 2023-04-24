<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<ol class="breadcrumb2" id="menuPath">
</ol>
<script>
    var _entryPath = "${fn:escapeXml(requestScope['javax.servlet.forward.servlet_path'])}";
    $(function () {
        var entryRoot = _entryPath;
        if(entryRoot.indexOf(".action") > -1){
            if(entryRoot.indexOf("_") > -1){
                entryRoot = entryRoot.substring(0, entryRoot.lastIndexOf("_")+1);
            }
            $(".sidenav .tree a[href^='" + _baseUrl + entryRoot + "']").parents('li').find('span').click();
        }
        var menuPath = generateMenuPath();
        menuPath.forEach(function (x) {
            $('#menuPath').append('<li class=\"breadcrumb-item2\">' + x.functionName + '</li>');
        });

    });

    function generateMenuPath() {
        var menu = _menuData;
        var menuItem = {};
        var path = [];
        menuItem.sub = [];
        var uri = _entryPath;
        menu.menuItemList.forEach(function (x) {
            menuItem.sub.push(x);
        });
        findFirstMenu(menuItem, uri, path);
        return path;
    }

    function findFirstMenu(menuItem, uri, path) {
        if (!menuItem) {
            return null;
        }
        if (Array.isArray(menuItem.sub) && menuItem.sub.length) {
            for (var i in menuItem.sub) {
                var menu = menuItem.sub[i];
                path.push(menu);
                var pick = findFirstMenu(menu, uri, path);
                if (pick)
                    return pick;
                path.pop();
            }
        }
        if (menuItem.url && menuItem.url.endsWith('.action') &&
            menuItem.url.substring(0, menuItem.url.lastIndexOf('_')).startsWith(
                uri.substring(0, uri.lastIndexOf("_"))
            )) {
            return menuItem;
        }
        return null;
    }
</script>

