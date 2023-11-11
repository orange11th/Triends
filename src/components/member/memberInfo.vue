<script setup>
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

const memberInfo = JSON.parse(route.params.memberInfo)

</script>
<template>
    <div>
        <h2>회원정보 페이지</h2>
        <h3>아이디: {{memberInfo.userId}}</h3>
        <h3>이름: {{memberInfo.userName}}</h3>
        <h3>이메일: {{memberInfo.email}}</h3>
        <h3>전화번호: {{memberInfo.phone}}</h3>
    </div>
</template>

<style scoped>
    
</style>