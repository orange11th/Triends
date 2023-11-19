<script setup>
import { ref, onMounted, reactive } from "vue";
import { useRouter } from "vue-router";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({
    team: Object
});

function movePlan(teamId) {
  router.push({ name: "plan-list", params: { teamId: teamId } });
}

const modalState = ref(false);

const openModal = (teamId) => {
    teamInviteList(
        teamId,
        ({ data }) => {
            inviteList.value = data;
        },
        console.error()
    );
    modalState.value = true;
};

const closeModal = () => {
    modalState.value = false;
};

const inviteList = ref([]);

function inviteTeam(teamId, userId) {
    teamInvite(
        teamId,
        userInfo.value.userId,
        userId,
        (response) => {
            console.log(response);
        },
        console.error()
    );
}

function leave(teamId){
    leaveTeam(teamId,userInfo.value.userId,console.log("leaveTeam"),console.error())
}

</script>

<template>
    <li class="team-item">
        <div class="team-header">
            <h2 class="team-info">{{ props.team.teamName }} <button @click="leave(props.team.teamId)">팀 나가기</button></h2>
        </div>
        <ul class="member-list">
            <button class="plan-link" @click="movePlan(props.team.teamId)">여행계획 바로가기</button>
            <div class="horizontal-scroll">
                <!-- 팀 내부 팀원 목록 -->
                <li v-for="teamMember in props.team.teamList" :key="teamMember.userId" class="member-item">
                    <span class="member-info">{{ teamMember.userName }}<br>{{ teamMember.userId }}</span>
                </li>
                <li class="member-item">
                    <a href="#" class="invite-link" @click="() => openModal(props.team.teamId)">초대하기</a>
                </li>
            </div>
        </ul>
        <!-- 모달 구현 -->
        <div v-show="modalState" class="modal-overlay" @click="closeModal">
            <div v-show="modalState" class="modal" @click.stop>
                <h1>teamId: {{ props.team.teamId }}</h1>
                <ul>
                    <li v-for="(invite, index) in inviteList" :key="index">
                        <span>{{ invite.userName }}</span>
                        <a href="" @click.prevent="inviteTeam(props.team.teamId, invite.userId)">({{ invite.userId }})</a>
                    </li>
                </ul>
                <button class="close-button" @click="closeModal">닫기</button>
            </div>
        </div>
    </li>
</template>

<style scoped>
.horizontal-scroll {
    overflow-x: auto; /* 가로 스크롤 활성화 */
    white-space: nowrap; /* 내용을 한 줄로 표시 */
    width: 100%; /* 적절한 너비 설정 */
    display: flex; /* 내부 요소를 가로로 정렬 */
    flex-wrap: nowrap; /* 요소들이 줄바꿈되지 않도록 설정 */
}

.team-item {
    background-color: #72b85c;
    margin: 50px 100px;
    padding: 10px;
    border-radius: 70px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.invite-link {
    text-decoration: none;
    color: white;
}

.member-list {
    display: flex;
    /* justify-content: space-between; */
    align-items: center;
    background-color: #f0f0f0;
    /* 배경색 추가 */
    padding: 10px;
    margin: 0 50px;
    margin-bottom: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 그림자 효과 추가 */
}

.team-info {
    color: white;
    text-align: center;
}

.plan-link {
    background-color: #007bff;
    color: white;
    padding: 5px 10px;
    font-size: 20px;
    text-decoration: none;
    border-radius: 15px;
    /* 버튼 모서리 둥글게 */
    margin: 0 20px;
}

.member-item {
    display: inline-flex; /* 내부 요소를 인라인 플렉스로 표시 */
    background-color: #41a788;
    color: white;
    padding: 10px;
    border-radius: 50%;
    /* 원형 모양 */
    width: 100px;
    /* 고정 너비 */
    height: 100px;
    /* 고정 높이 */
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-size: 30px;
    /* 글자 크기 조정 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin: 0 20px;
}

.member-info {
    color: white;
}
.modal {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    /* 절대 위치 */
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 800px;
    height: 600px;
    /* 추가 스타일 */
    background-color: rgba(221, 217, 217, 0.9);
    z-index: 2;
    border-radius: 80px;
    border-style: dashed;
}


.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0);
    /* 반투명 배경 */
    display: flex;
    justify-content: center;
    align-items: center;
}

.close-button {
    position: absolute;
    top: 30px;
    /* 상단으로부터의 거리 */
    right: 30px;
    /* 오른쪽으로부터의 거리 */
}
</style>
