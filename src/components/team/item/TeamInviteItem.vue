<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { myInviteList, registMember, deleteInvite } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const memberStore = useMemberStore();

const inviteList = ref({})

const props = defineProps({
    userId: String
});

onMounted(() => {
    myInviteList(
        props.userId
        , ({ data }) => {
            inviteList.value = data
        },
        console.error())

});

function accept(teamId) {
    registMember(
        teamId,
        props.userId,
        console.log("success"),
        console.error()
    )
}

function reject(teamId) {
    deleteInvite(
        teamId,
        props.userId,
        console.log("success"),
        console.error()
    )
}

</script>

<template>
    <div>
        <h2>내가 받은 초대 목록</h2>
        <ul>
            <li v-for="invite in inviteList" :key="invite.teamId">
                {{ invite.fromUserName }} ({{ invite.fromUserId }})님이 {{ invite.teamName }} ({{ invite.teamId }})팀에 초대하였습니다.
                <a href="" @click.prevent="accept(invite.teamId)">수락</a>
                / <a href="" @click.prevent="reject(invite.teamId)">거절</a>
            </li>
        </ul>
    </div>
</template>

<style scoped></style>