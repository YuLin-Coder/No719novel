$(document).ready(function(){
    var postStr = {
        'user_name':'',
        'user_pass':''
    };
    var selfCenterBtn = $("#selfCenterBtn");
    var loginOutBtn = $("#loginOutBtn");
    var loginInBtn = $("#loginInBtn");
    var regBtn = $("#regBtn");
    var infoField = $("#infoField");
    var loginField = $("#loginField");
    var user_name = $("#user_name");
    var user_pass = $("#user_pass");

    loginOutBtn.bind('click',function(){
        $.post('LoginOutSystem.action',null,
            function(responseObj) {
                if(responseObj.success) {
                    alert('�˳��ɹ���');
                    window.location.reload();
                }else  if(responseObj.err.msg){
                    alert('�˳��쳣��'+responseObj.err.msg);
                }else{
                    alert('�˳��쳣���������쳣��');
                }
            },'json');
    });

    var infos = "��ӭ����¼��<br/> <input type=\"button\" id=\"loginOutBtn\" class=\"btnstyle\" value=\"�˳�\"/>";
    loginInBtn.bind('click',function(){
        if(user_name.val()==''||user_pass.val()==''){
            alert("�û��������벻��Ϊ�գ�")
            return;
        }
        postStr['user_name'] = user_name.val();
        postStr['user_pass'] = user_pass.val();

        $.post('LoginInSystem.action',postStr,
            function(responseObj) {
                if(responseObj.success) {
                    var left_days = Number(responseObj.data.left_days);
                    alert('��¼�ɹ���');
                    if(left_days>0 && left_days<=3){
                        alert('���Ļ�Ա��ݻ���'+left_days+'���Ҫ�����ˣ��뼰ʱ���ѣ�');
                    }
                    window.location.reload();
                }else  if(responseObj.err.msg){
                    alert('��¼�쳣��'+responseObj.err.msg);
                }else{
                    alert('��¼�쳣���������쳣��');
                }
            },'json');
    });

    regBtn.bind('click',function(){
        window.location.href="page_reg.action";
    });

    selfCenterBtn.bind('click',function(){
        window.location.href="page_myInfo.action";
    });
});