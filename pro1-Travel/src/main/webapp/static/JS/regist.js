var checkVC = 0;
var checkNN = 0;
window.onload=function (){
    var userName = aa("userName");
    var uname = userName.value;
    if (uname!=""){
        checkName()
    }
}
function checkPwd(){

    var passWordReg = /[\w]{8,}/;
    var passWord = aa("passWord");
    var pwd = passWord.value;
    var errPwd = aa("errPwd");
   if(pwd==""){
        errPwd.innerText="���벻��Ϊ��";
        document.getElementById("formidSubmit").disabled=true;
    } else if (!passWordReg.test(pwd)){
        errPwd.innerText="���볤�Ȳ������ڰ�λ";
        document.getElementById("formidSubmit").disabled=true;
    }else {
       errPwd.innerText="";
        document.getElementById("formidSubmit").disabled=false;
    }
}

function aa(id){
    return document.getElementById(id);
}

function checkName(){
    var errName = aa("errName");
    errName.innerText="";
    var userName = aa("userName");
    var uname = userName.value;
    $.post("user/secletUserName",{"username":uname},function (data){
        if(data=="true"){
            var errName = aa("errName");
            errName.innerText="�û����ѱ�ռ��";
            document.getElementById("formidSubmit").disabled=true;
        }else {
            var unameReg = /[0-9a-zA-Z]{6,16}/;
            var errName = aa("errName")
            if(uname==""){
                errName.innerText="�û�������Ϊ��";
                document.getElementById("formidSubmit").disabled=true;
            } else if (!unameReg.test(uname)){
                errName.innerText="������6-16λ���û���";
                document.getElementById("formidSubmit").disabled=true;
            }else {
                errName.innerText="";
                document.getElementById("formidSubmit").disabled=false;
            }
        }
    })

     checkNN = checkNN + 1;
}


function reCheckPwd(){

    var passWordReg = /[\w]{8,}/;

    var passWord = aa("passWord");
    var pwd = passWord.value;

    var rePassWord = aa("rePassWord");
    var rePwd = rePassWord.value;

    var reErrPwd = aa("reErrPwd");
    if (rePwd==""){
        reErrPwd.innerText="���ٴ���������";
        document.getElementById("formidSubmit").disabled=true;
    }
    else if (rePwd != pwd){
        reErrPwd.innerText="�����������벻һ��";
        document.getElementById("formidSubmit").disabled=true;
    }else if (!passWordReg.test(rePwd)){
        reErrPwd.innerText="���볤�Ȳ������ڰ�λ";
        document.getElementById("formidSubmit").disabled=true;
    }
    else {
        reErrPwd.innerText="";
        document.getElementById("formidSubmit").disabled=false;
    }
    reCheckPwd();
}

function checkCharName(){


    var charNameReg = /^[\u4E00-\u9FA5]{2,10}(��[\u4E00-\u9FA5]{2,10}){0,2}$/;
    var charName = aa("charName");
    var cName = charName.value;
    var errCharName = aa("errCharName");
    if(cName==""){
        errCharName.innerText="��������Ϊ��";
        document.getElementById("formidSubmit").disabled=true;
    } else if (!charNameReg.test(cName)){
        errCharName.innerText="������ʽ����ȷ";
        document.getElementById("formidSubmit").disabled=true;
    }else {
        errCharName.innerText="";
        document.getElementById("formidSubmit").disabled=false;
    }
}
function checkAll(){
    var userName = aa("userName");
    var uname = userName.value;
    if(uname==""){
        var errName = aa("errName")
        errName.innerText="�û�������Ϊ��";
        return false;
    }
    if(checkNN == 0){
        var errName = aa("errName")
        errName.innerText="���ٴ������û���";
        return false;
    }
    var passWord = aa("passWord");
    var pwd = passWord.value;
    if(pwd==""){
        var errPwd = aa("errPwd");
        errPwd.innerText="���벻��Ϊ��";
        return false;
    }
    var rePassWord = aa("rePassWord");
    var rePwd = rePassWord.value;
    if(rePwd==""){
        var reErrPwd = aa("reErrPwd");
        rePassWord.innerText="���ٴ���������";
        return false;
    }
    var charName = aa("charName");
    var cName = charName.value;
    if(cName==""){
        var errCharName = aa("errCharName");
        errCharName.innerText="��������Ϊ��";
        return false;
    }
    var verifyCodeImg = aa("verifyCodeImg").value;
    if (verifyCodeImg==""){
        var errVerifyCode = aa("errVerifyCode");
        errVerifyCode.innerText="��֤�벻��Ϊ��";
        return false;
    }
    if(checkVC == 0){
        var errVerifyCode = aa("errVerifyCode");
        errVerifyCode.innerText="������������֤�룬�ٴ��ύ";
        return false;
    }
    return true;
}

function checkVerifyCode(){
    var verifyCodeImg = aa("verifyCodeImg").value;
    $.post("user/checkVarifyCode",{"VerifyCode":verifyCodeImg,"session":this.session},function (data){
        if(data=="true"){
            var errVerifyCode = aa("errVerifyCode");
            errVerifyCode.innerText="";
            document.getElementById("formidSubmit").disabled=false;
        }
        else {
            var errVerifyCode = aa("errVerifyCode");
            errVerifyCode.innerText="��֤���������";
            var imgVerifyCode=aa("imgVerifyCode");
            imgVerifyCode.src='verifyCode.jpg?d='+ Math.random();
            document.getElementById("formidSubmit").disabled=true;
        }
    })
    checkVC = checkVC + 1;
}


