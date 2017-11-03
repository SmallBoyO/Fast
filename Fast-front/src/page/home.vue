<template>
    <el-row class="container">
		<el-col :span="24" class="header">
			<el-col :span="10" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
				系统
			</el-col>
			<el-col :span="10">
				<div class="tools">
					<i class="fa fa-align-justify"></i>
				</div>
			</el-col>
			<el-col :span="4" class="userinfo">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" /> {{correntUserName}}</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>我的消息</el-dropdown-item>
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item divided>退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>
		<el-col :span="24" class="main">
		<aside>
			<el-menu default-active="1" class="el-menu-vertical-demo" style="min-height:800px"  @select="handleSelect">
                            <template v-for="(item,index) in menu">
                                <template v-if="item.type=='url'">
                                    <el-menu-item :index="item.name+ '-'+item.component"><i :class="[item.icon]"></i>{{item.name}}
                                    </el-menu-item>
                                </template>
                                <template v-else-if="item.type=='menu'">
                                    <el-submenu :index="item.name+ '-'+item.component">
                                        <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                                        <template v-for="(childitem,childindex) in item.child">
                                            <template v-if="childitem.type=='url'">
                                                <el-menu-item :index="childitem.name+ '-'+childitem.component">{{childitem.name}}
                                                </el-menu-item>
                                            </template>
                                            <template v-else-if="childitem.type=='menu'">
                                                <el-submenu :index="childitem.name + '-'+childitem.component">
                                                    <template slot="title"><i
                                                            class="el-icon-message"></i>{{childitem.name}}
                                                    </template>
                                                    <template v-for="(child2item,child2index) in childitem.child">
                                                        <template v-if="child2item.type=='url'">
                                                            <el-menu-item :index="child2item.name+ '-'+child2item.component">
                                                                {{child2item.name}}
                                                            </el-menu-item>
                                                        </template>
                                                    </template>
                                                </el-submenu>
                                            </template>
                                        </template>
                                    </el-submenu>
                                </template>
                            </template>
                        </el-menu>
						</aside>
						<section class="content-container">
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="breadcrumb-container">
						<strong class="title">{{$route.name}}</strong>
						<el-breadcrumb separator="/" class="breadcrumb-inner">
							<el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
								{{ item.name }}
							</el-breadcrumb-item>
						</el-breadcrumb>
					</el-col>
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>
				</div>
			</section>
		</el-col>
	</el-row>
</template>
<script type="text/ecmascript-6">
	import axios from 'axios';
	import qs from 'qs';
	axios.defaults.withCredentials=true;
    export default {
        data() {
			return{
				searchCriteria: '',
                breadcrumbItems: ['导航一'],
                menu: []
			}
		},
		computed:{
			correntUserName:function(){
				return this.$store.state.correntUser.name;
			}
		},
		methods:{
			handleSelect(key,keyPath) {
				console.log(key);
  				console.log(key.split('-')[1]);
				console.log(keyPath);
				console.log(keyPath);
				if((key.split('-')[1])!='null'){
					this.$router.push(key.split('-')[1]);
				}
            },
			getname(){
				return this.$store.state.correntUser.name;
			}
		},
		created(){
			console.log(this.$store.state.menu);
			if(this.$store.state.menu === null){
				axios.post(`http://127.0.0.1:8081/ajax/getUserMenu`).then(res => res.data).then(data => {
						this.menu=data;
					});
			}
		}
    }
</script>
<style>
.container {
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 1910px;
}
.container .header{
    height: 60px;
    line-height: 60px;
    background: #20a0ff;
    color: #fff;
}
.container .header .userinfo{
      text-align: right;
      padding-right: 35px;
      float: right;
}
.container .header .userinfo .userinfo-inner {
        cursor: pointer;
        color: #fff;
}
.container .header .userinfo .userinfo-inner img{
          width: 40px;
          height: 40px;
          border-radius: 20px;
          margin: 10px 0px 10px 10px;
          float: right;
}
.container .header .logo {
      height: 60px;
      font-size: 22px;
      padding-left: 20px;
      padding-right: 20px;
      border-color: rgba(238, 241, 146, 0.3);
      border-right-width: 1px;
      border-right-style: solid;
}
.container .header .logo img {
        width: 40px;
        float: left;
        margin: 10px 10px 10px 18px;
}
.container .header .logo .txt {
        color: #fff;
}
.container .header .logo-width {
      width: 230px;
}
.container .header .logo-collapse-width {
      width: 60px;
}
.container .header .tools{
      padding: 0px 23px;
      width: 14px;
      height: 60px;
      line-height: 60px;
      cursor: pointer;
}
.container .main{
    display: flex;
    position: absolute;
    top: 60px;
    bottom: 0px;
    overflow: hidden;
}
.container .main aside {
      flex: 0 0 230px;
      width: 230px;
}
.container .main aside .el-menu{
        height: 100%;
}
.container .main aside .collapsed{
        width: 60px;
}
.container .main aside .collapsed .item {
          position: relative;
}
.container .main aside .collapsed .submenu{
          position: absolute;
          top: 0px;
          left: 60px;
          z-index: 99999;
          height: auto;
          display: none;
}
.container .main .menu-collapsed{
      flex: 0 0 60px;
      width: 60px;
}
.container .main .menu-expanded {
      flex: 0 0 230px;
      width: 230px;
}
.container .main .content-container {
      flex: 1;
      overflow-y: scroll;
      padding: 20px;
}
.container .main .content-container .breadcrumb-container .title {
        width: 200px;
        float: left;
        color: #475669;
}
.container .main .content-container .breadcrumb-container .breadcrumb-inner {
        float: right;
}
.container .main .content-container .content-wrapper{
		margin-top: 10px;
        background-color: #fff;
        box-sizing: border-box;
}
</style>
