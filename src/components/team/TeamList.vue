<script setup>
import { ref, onMounted, reactive } from "vue";
import { myTeamList, teamInviteList, teamInvite } from "@/api/team";
import { useRouter } from "vue-router";
const router = useRouter();

const userId = "ssafy";

const teamList = ref([]);

function getTeamList() {
  myTeamList(
    //여기 나중에 로그인으로 바꿔야댐
    userId,
    ({ data }) => {
      teamList.value = data;
    },
    console.error()
  );
}

onMounted(() => {
  getTeamList();
});

const modalStates = reactive(new Map());

// 초기화
teamList.value.forEach((team) => {
  modalStates.set(team.teamId, false);
});

// 모달 열기
const openModal = (teamId) => {
  teamInviteList(
    teamId,
    ({ data }) => {
      inviteList.value = data;
    },
    console.error()
  );
  modalStates.set(teamId, true);
};

// 모달 닫기
const closeModal = (teamId) => {
  modalStates.set(teamId, false);
};

const inviteList = ref([]);

function inviteTeam(teamId, userId) {
  teamInvite(
    teamId,
    userId,
    (response) => {
      console.log(response);
    },
    console.error()
  );
}
function movePlan(teamId) {
  router.push({ name: "plan-list", params: { teamId: teamId } });
}
</script>

<template>
  <div class="container">
    <h1 class="team-title">My team</h1>
    <ul class="team-list">
      <li v-for="team in teamList" :key="team.teamId" class="team-item">
        <div class="team-header">
          <h2 class="team-info">{{ team.teamName }}</h2>
        </div>
        <ul class="member-list">
          <button class="plan-link" @click="movePlan(team.teamId)">여행계획 바로가기</button>
          <li v-for="teamMember in team.teamList" :key="teamMember.userId" class="member-item">
            <span class="member-info">{{ teamMember.userId }}</span>
          </li>
          <li class="member-item">
            <a href="#" class="invite-link" @click="() => openModal(team.teamId)">초대하기</a>
          </li>
        </ul>
        <div
          v-show="modalStates.get(team.teamId)"
          class="modal-overlay"
          @click="() => closeModal(team.teamId)"
        >
          <div v-show="modalStates.get(team.teamId)" class="modal" @click.stop>
            <h1>teamId: {{ team.teamId }}</h1>
            <ul>
              <li v-for="(invite, index) in inviteList" :key="index">
                <span>{{ invite.userName }}</span>
                <a href="" @click.prevent="inviteTeam(team.teamId, invite.userId)"
                  >({{ invite.userId }})</a
                >
              </li>
            </ul>
            <button class="close-button" @click="() => closeModal(team.teamId)">닫기</button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.team-title {
  color: black;
  margin: 50px 100px;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.team-list {
  list-style: none;
  padding: 0;
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
  background-color: #f0f0f0; /* 배경색 추가 */
  padding: 10px;
  margin: 0 50px;
  margin-bottom: 10px;
  border-radius: 70px; /* 모서리 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
}

.team-info {
  color: white;
  text-align: center;
}

.team-info-container {
  display: flex; /* Flexbox 사용 */
  gap: 10px; /* 요소 사이 간격 */
}

.plan-link {
  background-color: #007bff;
  color: white;
  padding: 5px 10px;
  font-size: 20px;
  text-decoration: none;
  border-radius: 15px; /* 버튼 모서리 둥글게 */
  margin: 0 20px;
}

.member-item {
  background-color: #41a788;
  color: white;
  padding: 10px;
  border-radius: 50%; /* 원형 모양 */
  width: 100px; /* 고정 너비 */
  height: 100px; /* 고정 높이 */
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-size: 30px; /* 글자 크기 조정 */
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
  position: absolute; /* 절대 위치 */
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
  background-color: rgba(0, 0, 0, 0); /* 반투명 배경 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.close-button {
  position: absolute;
  top: 30px; /* 상단으로부터의 거리 */
  right: 30px; /* 오른쪽으로부터의 거리 */
  /* 버튼에 대한 추가 스타일링 */
}
</style>
