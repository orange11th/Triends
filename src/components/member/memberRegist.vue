<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { memberRegist } from "@/api/member";

const router = useRouter()

const param = reactive({
    userId: "",
    userPass: "",
    userName: "",
    email: "",
    phone: "",
})

function regist() {
    memberRegist(param, (response) => { alert('회원가입 성공');console.log('성공');moveLogin(); }, (error) => { alert('회원가입 실패!');console.error() })
}

function moveLogin(){
    router.push({name:"member-login"})
}

</script>

<template>
    <div>
        <h2>회원가입 페이지</h2>
    </div>
    <div>
        <div>
            <input class="regist-input" type="text" placeholder="아이디" v-model="param.userId" />
        </div>
        <div>
            <input class="regist-input" type="password" placeholder="패스워드" v-model="param.userPass" />
        </div>
        <div>
            <input class="regist-input" type="text" placeholder="닉네임" v-model="param.userName" />
        </div>
        <div>
            <input class="regist-input" type="text" placeholder="이메일" v-model="param.email" />
        </div>
        <div>
            <input class="regist-input" type="pastextsword" placeholder="전화번호" v-model="param.phone" />
        </div>
        <div>
            <button class="regist-button" @click="regist">회원가입</button>
        </div>
    </div>
</template>
<style scoped>
.regist-input {
    width: 350px;
    height: 45px;
    background-color: rgb(231, 231, 231);
    border: 0px;
    padding-left: 15px;
    border-radius: 15px;
    margin: 10px;
}

.regist-button {
    margin: 10px;
}
</style>