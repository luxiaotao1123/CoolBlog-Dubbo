<template>
  <div class="authorInfo-box">
   <Card style="  " class="info-r">
       <transition name="component-fade" mode="out-in">
        <div style="text-align:center" v-if="id == 5" >
            <img src="https://tvax3.sinaimg.cn/crop.0.19.670.670.180/006TYhdCly8fi3z75745fj30j60j6aeb.jpg" class="about-sel">
            <h3>Tom</h3>
            <p>Every thing changed but Tom </p>
            <div class="info-social">
                <ul class="social-ul">
                    <li>
                        <a href="https://github.com/ranmogit"  target="_blank"><Icon type="social-github"></Icon></a>
                    </li>
                    <li>
                        <a href="https://twitter.com/calvino_twi"><Icon type="social-twitter"></Icon></a>
                    </li>
                </ul>
            </div>
        </div>
        <div style="text-align:center" v-if="id == 4" >
            <img src="https://sfault-avatar.b0.upaiyun.com/284/938/2849383166-59fbc62406de4_huge256" class="about-sel">
            <h3>Vincent</h3>
            <p>Talk is cheap, show me the code</p>
            <div class="info-social">
                <ul class="social-ul">
                    <li>
                        <a href="https://github.com/luxiaotao1123"  target="_blank"><Icon type="social-github"></Icon></a>
                    </li>
                    <li>
                        <a href="#"><Icon type="social-twitter"></Icon></a>
                    </li>
                </ul>
            </div>
        </div>
         </transition>
    </Card>
  </div>
</template>
<script>
export default {
  data () {
    return {
      id: ''
    }
  },
  mounted () {
    let id = this.$route.params.id
    console.log('id =' + id)
    this.id = id
  }
}
</script>

<style scoped>
.component-fade-enter-active, .component-fade-leave-active {
  transition: all 1s ease;
 
}
.component-fade-enter, .component-fade-leave-to
/* .component-fade-leave-active for below version 2.1.8 */ {
    height :0px;
  opacity: 0;
}
.authorInfo-box{
    background-image: url(/static/images/pbear.jpg);
    /* background-attachment: fixed; */
    width:1200px;
    min-width: 1200px;
    height:800px;
    background-repeat: no-repeat;
    
}
.info-r{
    transition: all 1s ease;
    position: absolute; top: 40%;transform: translateY(-50%);left:40%;width:320px;
}
.authorInfo-box a{
        color: #333;
        font-size: 1.8em
        
}
.authorInfo-box p{
        word-wrap: break-word;
        font-family: sans-serif
}
.about-sel{
        width: 110px;
    height: 110px;
    border-radius: 55px;
}
.info-social{
        margin-top: 15px;
    text-align: center;
}
.social-ul{
        list-style: none;
    display: inline;
    margin: 0;
    padding: 0;
    vertical-align: text-bottom;
    
}
.social-ul li{
        margin-right: 5px;
        display: inline-block;
}
.social-ul li a{
        width: 26px;
    height: 26px;
}
@media screen and (max-width: 1024px)  {
    .authorInfo-box{
        width:100%;
        min-width: 800px;
    }
}
@media screen and (max-width: 420px){
     .authorInfo-box{
        width:100%;
        min-width: 300px;
        position: relative;
    }
    .info-r{
 width: 280px;
 left: 3%;
 top: 30%
    }
}
</style>
