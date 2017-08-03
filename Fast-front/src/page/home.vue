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
					<span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" /> 用户名</span>
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
                                <template v-if="item.type==1">
                                    <el-menu-item :index="item.name"><i :class="[item.icon]"></i>{{item.name}}
                                    </el-menu-item>
                                </template>
                                <template v-else-if="item.type==2">
                                    <el-submenu :index="item.name">
                                        <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                                        <template v-for="(childitem,childindex) in item.child">
                                            <template v-if="childitem.type==1">
                                                <el-menu-item :index="childitem.name">{{childitem.name}}
                                                </el-menu-item>
                                            </template>
                                            <template v-else-if="childitem.type==2">
                                                <el-submenu :index="childitem.name">
                                                    <template slot="title"><i
                                                            class="el-icon-message"></i>{{childitem.name}}
                                                    </template>
                                                    <template v-for="(child2item,child2index) in childitem.child">
                                                        <template v-if="child2item.type==1">
                                                            <el-menu-item :index="child2item.name">
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
    export default {
        data() {
			return{
				searchCriteria: '',
                breadcrumbItems: ['导航一'],
                menu: [{name: '导航一', icon: "el-icon-message", type: 1}, {name: '导航2', type: 1}, {
                    name: '导航3',
                    type: 1
                }, {
                    name: '导航42',
                    type: 2,
                    child: [{name: '菜单1', type: 1, child: []}, {
                        name: '菜单2',
                        type: 2,
                        child: [{name: '菜单3', type: 1, child: []}]
                    }]
                }]
			}
		},
		methods:{
			handleSelect(key,keyPath) {
				console.log(key);
				console.log(keyPath);
				 this.$router.push('/home/counter');
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
        background-color: #fff;
        box-sizing: border-box;
}
</style>