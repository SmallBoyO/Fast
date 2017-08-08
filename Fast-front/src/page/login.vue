<template>
    <el-form :model="formdata" :rules="rules" ref="formdata" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="formdata.username" auto-complete="off" placeholder="账号" ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="formdata.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" :loading="loading" @click="submitForm('formdata')">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script type="text/ecmascript-6">
	import axios from 'axios';
	import qs from 'qs';
	axios.defaults.withCredentials=true;
	
    export default {
        data() {
            return {
				loading:false,
				formdata:{
					username:'zhang',
					password:'123456'
				},
				rules:{
					username:[
						{required:true,message:'请输入用户名',trigger: 'blur'},
						{min:5,max:16,message:'用户名长度在5到16之间',trigger: 'blur'}
					],
					password:[
						{required:true,message:'请输入密码',trigger: 'blur'},
						{min:5,max:16,message:'密码长度在5到16之间',trigger: 'blur'}
					]
				}
			}
        },
		methods:{
			submitForm(formName) {
				//console.log(this.$refs);
				this.$refs[formName].validate((valid) => {
				  if (valid) {
					this.loading=true;
					axios.post(`http://127.0.0.1:8081/ajax/loginajax`,qs.stringify(this.formdata)).then(res => res.data).then(data => {
						if(data.ret == 1){
							this.loading=false;
							this.$store.commit('login',{username:data.obj.userName,name:data.obj.name});
							this.$router.push('/home');
						}else{
							this.loading=false;
							this.$message({
							  message: data.message,
							  type: 'error'
							});
							//this.$message.error(data.message);
						}
					});
				  
				  } else {
					console.log('error submit!!');
					return false;
				  }
				});
			 },
			 checkLogin(){
				return this.$store.state.correntUser.islogin;
			 }
		}
    }
</script>
<style>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 7px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
	}
    .login-container .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
	.login-container .remember {
      margin: 0px 0px 35px 0px;
    }
</style>