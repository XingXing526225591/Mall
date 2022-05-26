
window.onload=function(){
    var vue = new Vue({
        el:"#mainDiv",
        data:  {
            count:1,
            webPage:1,
            travelId: 0,
            travelList: {}
        },
        methods:{
            getTravels:function (page){
                axios({
                    method:"POST",
                    url:"/travel/selectByUserId",
                    params:{
                        page:page,
                    }
                }).then(function (value) {
                    var list = value.data
                    vue.travelList = list;
                }).catch(function (reason){

                })
            },getCount:function (){
                axios({
                    method:"POST",
                    url:"/travel/getCount",
                }).then(function (value) {
                    vue.count = value.data
                }).catch(function (reason){

                })
            },travelDetail:function (travelId) {
                window.location.href="/travel/details/"+travelId;
            },
            deleteTravel:function (travelId){
                axios({
                    method:"POST",
                    url: "/travel/delete/"+travelId,
                    params:{
                        travelId:travelId
                    }
                }).then( (value) => {
                    this.getTravels(vue.webPage);
                }).catch(function (reason){

                })
            },
            clickDelete: async function (travelId) {
                if (window.confirm("您是否确认删除此条信息？")) {
                    this.deleteTravel(travelId)
                }
            },clickUpdate:function (tId){
                window.location.href="/travel/updateJump/"+tId;
            },addTravelPage:function(){
                if(vue.webPage < vue.count) {
                    vue.webPage = vue.webPage + 1;
                    this.getTravels(vue.webPage);
                }
            },
            subTravelPage:function (){
                if(vue.webPage > 1){
                    vue.webPage = vue.webPage - 1;
                    this.getTravels(vue.webPage);
                }
            }

        },mounted:function (){
            this.getTravels(1);
            this.getCount();
        }
    })
}