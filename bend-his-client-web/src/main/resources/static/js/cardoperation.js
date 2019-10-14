

$(function(){
    //选择身份证
    $("#icCardRead").click(function () {

        var cardInfoData=$("#cardInfo").val();
        if (cardInfoData==="")
        {
            alert("身份号不能为空!!!")
            $("#icCardRead").attr("checked",false)
            $("#medicalInsuranceRead").attr("checked",false)
        }else
        {
            var activeX = document.getElementById("CSharpActiveX");
            var param="<PI_CRBZ>1</PI_CRBZ><PI_SFBZ>"+cardInfoData+"</PI_SFBZ>"
            alert(activeX.ResidentMedicalInsuranceMethod("GetUserInfo",param));
        }

    });
//选择医保读卡
    $("#medicalInsuranceRead").click(function () {
        $("#cardInfo").val("");

    });
});