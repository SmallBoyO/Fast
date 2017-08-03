<template>
    <div style="background-color: #EBEBEB;min-height:800px">
        <div style="width:100%;background-color: #636363; overflow: hidden">
            <span class="demonstration" style="float:left;padding-top:10px;color:white;margin-left:1%">
                网站首页
            </span>
            <span class="demonstration" style="float:left;padding:5px;color:white;margin-left:2%;width:15%">
                <el-input
                        placeholder="请输入"
                        icon="search"
                        v-model="searchCriteria"
                        :on-icon-click="handleIconClick">
                </el-input>
            </span>
            <span class="demonstration" style="float:right;padding-top:10px;margin-right:1%">
                <el-dropdown trigger="click">
                  <span class="el-dropdown-link" style="color:white">
                    admin<i class="el-icon-caret-bottom el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人信息</el-dropdown-item>
                    <el-dropdown-item>退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
            </span>
        </div>

        <div style="margin-top:5px">
            <el-row :gutter="10">
                <el-col :xs="4" :sm="4" :md="4" :lg="4">
                    <div>
                        <el-menu default-active="1" class="el-menu-vertical-demo" style="min-height:800px"  @select="handleSelect">
                            <!--<el-submenu index="1">
                                <template slot="title"><i class="el-icon-message"></i>导航一</template>
                                <el-menu-item-group>
                                    <template slot="title">分组一</template>
                                    <el-menu-item index="1-1">选项1</el-menu-item>
                                    <el-menu-item index="1-2">选项2</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group title="分组2">
                                    <el-menu-item index="1-3">选项3</el-menu-item>
                                </el-menu-item-group>
                                <el-submenu index="1-4">
                                    <template slot="title">选项4</template>
                                    <el-menu-item index="1-4-1">选项1</el-menu-item>
                                </el-submenu>
                            </el-submenu>-->
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
                    </div>
                </el-col>
                <el-col :xs="20" :sm="20" :md="20" :lg="20">
                    <!--<div>
                        <div style="border: 1px solid #A6A6A6; border-radius:6px; padding:5px; margin:2px; background-color: white">
                            <router-view></router-view>
                            <el-breadcrumb separator="/">
                                <el-breadcrumb-item v-for="item in breadcrumbItems">{{item}}</el-breadcrumb-item>
                            </el-breadcrumb>
                        </div>
                    </div>-->
                    <div style="margin-top:10px">
                        <router-view></router-view>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script type="text/ecmascript-6">
    export default {
        data() {
            return {
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

        methods: {
            handleIconClick(ev) {
                console.log(ev);
            },
            handleSelect(key,keyPath) {
				console.log(key);
				console.log(keyPath);
				 switch(key){
                    case '导航2':
						this.$router.push('/menu1');
						break;
					case '导航3':
						this.$router.push('/menu2');
						break;
				}
            }
        },
    }
</script>