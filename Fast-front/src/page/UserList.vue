<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="searchData">
                <el-form-item>
                    <el-input placeholder="姓名" v-model="searchData.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="searchData.status" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-button @click="search">搜索</el-button>
                <el-button v-if="right.systemuseradd" @click="addUser">添加用户</el-button>
            </el-form>

        </el-col>
        <el-table v-loading="listLoading" :data="tableData" height="700" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="userName" label="账号" width="180">
            </el-table-column>
            <el-table-column prop="name" label="用户名" width="180">
            </el-table-column>
            <el-table-column label="状态">
              <template scope="scope">
                <p v-if='scope.row.status==1'>启用</p>
                <p v-if='scope.row.status==2'>禁用</p>
              </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" v-if="right.systemuserupdate"  @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button size="small" v-if="right.systemuserdelete" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="searchData.correntPage" :page-sizes="[10, 20, 50, 100, 200]" :page-size="searchData.pageSize"
                       layout="total, sizes, prev, pager, next, jumper" :total="searchData.total">
        </el-pagination>

        <el-dialog v-if="right.systemuserupdate" title="编辑用户" size="tiny" :visible.sync="dialogFormVisible">
            <el-form :inline="false" :rules="rules" ref="editform" :model="editData" label-width="80px">
                <el-form-item label="用户名" prop="name">
                    <el-input placeholder="用户名" v-model="editData.name"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="editData.status" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色" prop="role">
                    <el-checkbox-group v-model="editRoles">
                        <el-checkbox v-for="item in rolelist" :label="item.role" name="sss"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="danger" @click="submitEdit('editform')">提交修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog v-if="right.systemuseradd" title="添加用户" size="tiny" :visible.sync="dialogaddFormVisible">
            <el-form :inline="false" :rules="addrules" ref="addform" :model="addData" label-width="80px">
                <el-form-item label="账号" prop="userName">
                    <el-input placeholder="账号" v-model="addData.userName"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="name">
                    <el-input placeholder="用户名" v-model="addData.name"></el-input>
                </el-form-item>
                <el-form-item  label="密码" prop="password">
                    <el-input type="password" placeholder="密码" v-model="addData.password"></el-input>
                </el-form-item>
                <el-form-item  label="密码确认" prop="password2">
                    <el-input type="password" placeholder="请再次输入密码" v-model="addData.password2"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="role">
                    <el-checkbox-group v-model="addRoles">
                        <el-checkbox v-for="item in rolelist" :label="item.role" name="sss"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('addform')">提交</el-button>
                    <el-button @click="resetForm('addform')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </section>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios';
    //import qs from 'qs';
    var qs = require('querystring')
    axios.defaults.withCredentials = true;
    export default {
        data() {
            var validateUserName = (rule, value, callback) => {
                if (value == '') {
                    callback(new Error('请输入账号！'));
                } else if (value.length < 5 || value.length > 16) {
                    callback(new Error('用户名长度在5到16之间！'));
                } else {
                    axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkUserName`, qs.stringify({userName: value})).then(res => res.data).then(data => {
                        console.log(data);
                        if (data.ret == -1) {
                            callback(new Error(data.message));
                        } else {
                            callback();
                        }
                    });
                }

            };
            var validateName = (rule, value, callback) => {
                if (value == '') {
                    callback(new Error('请输入用户名！'));
                } else if (value.length < 5 || value.length > 16) {
                    callback(new Error('用户名长度在5到16之间！'));
                } else {
                    axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkName`, qs.stringify({
                        name: value,
                        id: this.editData.id
                    })).then(res => res.data).then(data => {
                        console.log(data);
                        if (data.ret == -1) {
                            callback(new Error(data.message));
                        } else {
                            callback();
                        }
                    });
                }

            };
            var validateAddName = (rule, value, callback) => {
                if (value == '') {
                    callback(new Error('请输入用户名！'));
                } else if (value.length < 5 || value.length > 16) {
                    callback(new Error('用户名长度在5到16之间！'));
                } else {
                    axios.post(`http://127.0.0.1:8081/ajax/UserManager/checkName`, qs.stringify({name: value})).then(res => res.data).then(data => {
                        console.log(data);
                        if (data.ret == -1) {
                            callback(new Error(data.message));
                        } else {
                            callback();
                        }
                    });
                }

            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if (value.length < 8) {
                    callback(new Error('密码长度不能小于8'));
                } else {
                    if (this.addData.password2 !== '') {
                        this.$refs.addform.validateField('password2');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if (value !== this.addData.password) {
                    callback(new Error('两次填写的密码必须一致'));
                } else {
                    callback();
                }
            };
            return {
                dialogFormVisible: false,
                dialogaddFormVisible: false,
                listLoading: false,
                tableData: [],
                multipleSelection: [],
                right:{},
      					rolelist:[],
                searchData: {
                    name: '',
                    status: '',
                    correntPage:1,
                    pageSize:10,
                    total:10
                },
                addData: {
                    name: '',
                    userName: '',
                    password: '',
                    password2: '',
                    status: 1
                },
                addRoles:[],
                editData: {
                    id: 0,
                    userName: '',
                    password: '',
                    name: '',
                    status: '1'
                },
                editRoles:[],
                options: [
                    {
                        value: '1',
                        label: '启用'
                    }, {
                        value: '2',
                        label: '禁用'
                    }
                ],
                rules: {
                    name: [
                        {validator: validateName, trigger: 'blur'}
                    ]
                },
                addrules: {
                    name: [
                        {validator: validateAddName, trigger: 'blur'}
                    ],
                    userName: [
                        {validator: validateUserName, trigger: 'blur'}
                    ],
                    password2: [
                        {validator: validatePass2, trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatePass, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            getRight(){
              axios.post(`http://127.0.0.1:8081/ajax/getRight`,qs.stringify({id:this.$route.params.rightid})).then(res => res.data).then(data => {
        					console.log(data);
                  this.right = data;
        			});
            },
            getRoleList(){
        			axios.post(`http://127.0.0.1:8081/ajax/UserManager/getRoleList`,qs.stringify({})).then(res => res.data).then(data => {
        					this.rolelist = data.result;
        			});
      			},
            handleSelectionChange(val) {
                multipleSelection = val;
            },
            handleEdit(index, row) {
                axios.post(`http://127.0.0.1:8081/ajax/UserManager/getUserRoleList`,qs.stringify({id:row.id})).then(res => res.data).then(data => {
                    this.editRoles = data.obj;
                    this.dialogFormVisible = true;
                    this.editData.id = row.id;
                    this.editData.name = row.name;
                    this.editData.status = row.status;
                });
                console.log(index, row);
            },
            handleDelete(index, row) {
                this.$confirm('确认删除？')
                .then(()=> {
                    axios.post(`http://127.0.0.1:8081/ajax/UserManager/deleteUser`, qs.stringify({id:row.id}))
                    .then(res =>res.data).then(data => {
                        if (data.ret == 1) {
                            this.$alert(data.message,"");
                            this.search();
                        } else if (data.ret == -100) {
                            this.listLoading = false;
                            this.$store.commit('setMenu',null);
                            this.$router.push('/login');
                        } else{
                            this.$alert(data.message,"");
                        }
                    });
                })
                .catch(() => {});
                console.log(index, row);
            },
            handleSizeChange(val) {
                this.searchData.pageSize = val;
                console.log(`每页 ${val} 条`);
                this.search();
                console.log(this.searchData.pageSize);
            },
            handleCurrentChange(val) {
                this.searchData.correntPage = val;
                this.search();
                console.log(`当前页: ${val}`);
                console.log(this.searchData.correntPage);
            },
            search() {
                this.listLoading = true;
                axios.post(`http://127.0.0.1:8081/ajax/UserManager/userList`, qs.stringify(this.searchData)).then(res => res.data).then(data => {
                    if (data.ret == 1) {
                        this.tableData = data.page.result;
                        this.searchData.correntPage = data.page.correntPage;
                        this.searchData.pageSize = data.page.pageSize;
                        this.searchData.total = data.page.total;
                        this.listLoading = false;
                    } else if (data.ret == -100) {
                        this.listLoading = false;
                        this.$store.commit('setMenu',null);
                        this.$router.push('/login');
                    }
                });
            },
            addUser() {
                this.dialogaddFormVisible = true;
            },
            submitEdit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let rolestr='';
                        for(let role in this.editRoles){
                          rolestr+=('&'+'rolelist='+this.editRoles[role]);
                        }
                        axios.post(`http://127.0.0.1:8081/ajax/UserManager/updateUser`, qs.stringify(this.editData)+rolestr).then(res => res.data).then(data => {
                            if (data.ret == 1) {
                                this.$alert(data.message, '', {
                                    confirmButtonText: '确定'
                                });
                                this.editData = {
                                    uerName: '',
                                    password: '',
                                    name: '',
                                    status: 1
                                };
                                this.search();
                                this.dialogFormVisible = false;
                            } else if (data.ret == -99) {
                                this.$alert(data.message, '', {
                                    confirmButtonText: '确定'
                                });
                                this.dialogFormVisible = false;
                            } else if (data.ret == -100) {
                                this.$store.commit('setMenu',null);
                                this.$router.push('/login');
                            } else {
                                this.$alert(data.message, '', {
                                    confirmButtonText: '确定'
                                });
                            }
                        });
                    }
                });
            },
            resetForm(formName){
                this.addRoles = [];
                this.$refs[formName].resetFields();
            },
            submitForm(formName){
                this.$refs[formName].validate((valid) => {
                    if(valid){
                        let rolestr='';
                        for(let role in this.addRoles){
                          rolestr+=('&'+'rolelist='+this.addRoles[role]);
                        }
                        var data = {
                            name:this.addData.name,
                            userName:this.addData.userName,
                            status:this.addData.status,
                            password:this.addData.password
                        }
                        axios.post("http://127.0.0.1:8081/ajax/UserManager/addUser",qs.stringify(data)+rolestr).then(res =>{
                            if(res.data.ret==1){
                                this.dialogaddFormVisible = false;
                                this.$alert(res.data.message, '', {
                                    confirmButtonText: '确定'
                                });
                                this.search();
                            }else if (res.data.ret == -100) {
                                this.$store.commit('setMenu',null);
                                this.$router.push('/login');
                            } else {
                                this.$alert(res.data.message, '', {
                                    confirmButtonText: '确定'
                                });
                            }
                        });
                    }
                });
            }
        },
        created(){
          console.log("--------------------");
          this.getRight();
          this.getRoleList();
          console.log(this.$route);
        }
    }
</script>
