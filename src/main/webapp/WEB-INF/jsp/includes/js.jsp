<script src='<c:url value="/js/jquery.min.js"/>'></script>
<script src='<c:url value="/js/noty.min.js"/>'></script>
<script src='<c:url value="/js/datatables.min.js"/>'></script>

<%--<script src='<c:url value="/webjars/js/dataTables.rowsGroup.js"/>'></script>--%>
<%--    <script src='<c:url value="/webjars/js/buttons.flash.min.js"/>'></script>--%>
<script src='<c:url value="/js/buttons.html5.min.js"/>'></script>
<script src='<c:url value="/js/buttons.print.min.js"/>'></script>
<%--<script src='<c:url value="/webjars/js/dataTables.responsive.min.js"/>'></script>--%>
<script src='<c:url value="/js/jszip.min.js"/>'></script>
<script src='<c:url value="/js/sockjs.min.js"/>'></script>
<script src='<c:url value="/js/stomp.min.js"/>'></script>
<script src='<c:url value="/js/base_function.js"/>'></script>
<script src='<c:url value="/js/base_onload.js"/>'></script>
<script src='<c:url value="/js/pagination.input.js"/>'></script>

<script>
    $(function () {
        var stompClient = null;
        var socket = new SockJS('<c:url value="/bli-websocket" />', null, {timeout: 3000});
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/user/queue/unlock', function (payload) {
                if (payload != null) {
                    var msg = JSON.parse(payload.body).message;
                    if (msg === "Unlock") {
                        unlockAllButtons();
                    }
                }
            });
        });
    });
</script>
