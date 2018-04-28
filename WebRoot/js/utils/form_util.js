/**表单元素回写工具
 * wumingkun 2014-08-19
 */
var FormUtil={
    //值回写方法
    set:function(obj){
        var attr;
        for(attr in obj){
            var element=$("#"+attr)[0];
            var nodeName=element.nodeName;
            if(nodeName=="INPUT"){
                FormUtil.judgeType($(element).attr("type"),attr,obj[attr]);
            }else if(nodeName=="SELECT"){
                FormUtil.setSelect(attr,obj[attr]);
            }else if(nodeName=="TEXTAREA"){
                FormUtil.setValue(attr,obj[attr]);
            }
        }
    },
    //设置文本框 文本域 密码框 隐藏框
    setValue:function(key,value){
        $("#"+key).val(value);
    },
    //设置下拉框
    setSelect:function(key,value){
        $("#"+key+" option").each(function(){
            if(this.value==value){
                $(this).attr("selected","selected");
            }
        });
    },
    //设置radio
    setRadio:function(key,value){
        $(":radio[name='"+key+"']").attr("checked",false);
        $(":radio[value='"+value+"']").attr("checked",true);
    },
    //设置checkbox
    setCheckbox:function(key,value,isDefault){
        $(":checkbox[name='"+key+"']").attr("checked",false);
        if(isDefault){
            var vs=value.split("");
            for(var i=vs.length-1;i>=0;i--){
                if(vs[i]=="1"){
                    $(":checkbox[name='"+key+"']").filter(":eq("+(vs.length-1-i)+")").attr("checked",true);
                }
            }
        }else {

        }
    },
    //input类型判断
    judgeType:function(type,key,value){
        if(type=="text"||type=="password"||type=="hidden"){
            FormUtil.setValue(key,value);
        }else if(type=="radio"){
            FormUtil.setRadio(key,value);
        }else if(type=="checkbox"){
            FormUtil.setCheckbox(key,value,true);
        }
    }
};

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}