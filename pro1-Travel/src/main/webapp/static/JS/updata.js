
function ProcessFile(e) {
    var file = document.getElementById('file').files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function (event) {
            var txt = event.target.result;

            var img = document.getElementById('filePng')
            img.src = txt;//��ͼƬbase64�ַ�����ֵ��img��src
            var errImg = aa("errAddImg");
            errImg.innerText="";
            document.getElementById("addFormBtn").disabled=false;
        };
    }
    reader.readAsDataURL(file);
}

function contentLoaded() {
    document.getElementById('file').addEventListener('change',
        ProcessFile, false);
}

window.addEventListener("DOMContentLoaded", contentLoaded, false);


function checkTitle(){
    var tilte = aa("realAddTitle").value;
    if(tilte==""){
        var errTitle = aa("errTitle");
        errTitle.innerText = "���������";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errTitle = aa("errTitle");
        errTitle.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}

function aa(id){
    return document.getElementById(id);
}
function checkRegion(){
    var region = aa("addRegionInput").value;
    if(region==""){
        var errRegion =   aa("errAddInput");
        errRegion.innerText = "���������";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errRegion =  aa("errAddInput");
        errRegion.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}

function checkContext(){
    var context = aa("addTextInput").value;
    if(context==""){
        var errcontext = aa("errContext");
        errcontext.innerText = "����������";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errcontext = aa("errContext");
        errcontext.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}



function addCheckAll(){
    var tilte = aa("realAddTitle").value;
    if(tilte==""){
        var errTitle = aa("errTitle");
        errTitle.innerText = "���������";
        return false;
    }
    var region = aa("addRegionInput").value;
    if(region==""){
        var errRegion =   aa("errAddInput");
        errRegion.innerText = "���������";
        return false;
    }
    var context = aa("addTextInput").value;
    if(context==""){
        var errcontext = aa("errContext");
        errcontext.innerText = "����������";
        return  false;
    }
    var img = aa("filePng");

    return true;
}