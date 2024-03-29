<%@include file="../../head.jsp" %>

<div class="panel-group ">
    <div class="panel panel-default ">
        <div class="panel-heading" >
            <h3 class="panel-title">Hor�rio</h3>
        </div>
        <p></p>
        <p></p>
        <div class="panel-body">

            <div style="width:700px;">
                <div class="left">
                    <table>
                        <tr>
                            <td><div class="item">English</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Science</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Music</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">History</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Computer</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Mathematics</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Arts</div></td>
                        </tr>
                        <tr>
                            <td><div class="item">Ethics</div></td>
                        </tr>
                    </table>
                </div>
                <div class="right">
                    <table>
                        <tr>
                            <td class="blank"></td>
                            <td class="title">Monday</td>
                            <td class="title">Tuesday</td>
                            <td class="title">Wednesday</td>
                            <td class="title">Thursday</td>
                            <td class="title">Friday</td>
                        </tr>
                        <tr>
                            <td class="time">08:00</td>
                            <td id="1" class="drop"></td>
                            <td id="2" class="drop"></td>
                            <td id="3" class="drop"></td>
                            <td id="4" class="drop"></td>
                            <td id="5" class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">09:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">10:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">11:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">12:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">13:00</td>
                            <td class="lunch" colspan="5">Lunch</td>
                        </tr>
                        <tr>
                            <td class="time">14:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">15:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                        <tr>
                            <td class="time">16:00</td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                            <td class="drop"></td>
                        </tr>
                    </table>
                </div>
            </div>


            <style type="text/css">
                .left{
                    width:120px;
                    float:left;
                }
                .left table{
                    background:#E0ECFF;
                }
                .left td{
                    background:#eee;
                }
                .right{
                    float:right;
                    width:570px;
                }
                .right table{
                    background:#E0ECFF;
                    width:100%;
                }
                .right td{
                    background:#fafafa;
                    color:#444;
                    text-align:center;
                    padding:2px;
                }
                .right td{
                    background:#E0ECFF;
                }
                .right td.drop{
                    background:#fafafa;
                    width:100px;
                }
                .right td.over{
                    background:#FBEC88;
                }
                .item{
                    text-align:center;
                    border:1px solid #499B33;
                    background:#fafafa;
                    color:#444;
                    width:100px;
                }
                .assigned{
                    border:1px solid #BC2A4D;
                }
                .trash{
                    background-color:red;
                }

            </style>

        </div>
    </div>
</div>

<%@include file="../../tail.jsp" %>

<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script>
    $(function () {
        $('.left .item').draggable({
            revert: true,
            proxy: 'clone'
        });
        $('.right td.drop').droppable({
            onDragEnter: function () {
                $(this).addClass('over');
            },
            onDragLeave: function () {
                $(this).removeClass('over');
            },
            onDrop: function (e, source) {
                $(this).removeClass('over');
                if ($(source).hasClass('assigned')) {
                    $(this).append(source);
                } else {
                    var c = $(source).clone().addClass('assigned');
                    $(this).empty().append(c);
                    c.draggable({
                        revert: true
                    });
                }
            }
        });
        $('.left').droppable({
            accept: '.assigned',
            onDragEnter: function (e, source) {
                $(source).addClass('trash');
            },
            onDragLeave: function (e, source) {
                $(source).removeClass('trash');
            },
            onDrop: function (e, source) {
                $(source).remove();
            }
        });
    });
</script>