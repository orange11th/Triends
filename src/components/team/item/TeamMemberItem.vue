<script setup>
import { ref, onMounted, reactive } from "vue";
import { useRouter, useRoute } from "vue-router";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import "@/assets/css/team/teamMember.css";

const router = useRouter();
const route = useRoute();

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({
  team: Object,
});

function movePlan(teamId) {
  router.push({ name: "plan-list", params: { teamId: teamId } });
}

const showTemplate = ref(true);
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
    () => {
      alert(`${userId} 초대 완료!`);
    },
    (error) => {
      alert("이미 초대한 사용자입니다.");
    }
  );
}

function leave(teamId) {
  leaveTeam(
    teamId,
    userInfo.value.userId,
    () => {
      showTemplate.value = false;
    },
    console.error()
  );
}
</script>

<template>
  <div v-if="showTemplate">
    <li class="team-item">
      <div class="team-header">
        <h2 class="team-info">
          {{ props.team.teamName }}
          <button @click="leave(props.team.teamId)">팀 나가기</button>
        </h2>
      </div>
      <ul class="member-list">
        <button class="plan-link" @click="movePlan(props.team.teamId)">
          여행계획 바로가기
        </button>
        <div class="horizontal-scroll">
          <!-- 팀 내부 팀원 목록 -->
          <li
            v-for="teamMember in props.team.teamList"
            :key="teamMember.userId"
            class="member-item"
          >
            <span class="member-info"
              >{{ teamMember.userName }}<br />{{ teamMember.userId }}</span
            >
          </li>
          <li class="member-item">
            <a
              href="#"
              class="invite-link"
              @click="() => openModal(props.team.teamId)"
              >초대하기</a
            >
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
              <a
                href=""
                @click.prevent="inviteTeam(props.team.teamId, invite.userId)"
                >({{ invite.userId }})</a
              >
            </li>
          </ul>
          <button class="close-button" @click="closeModal">닫기</button>
        </div>
      </div>
    </li>
  </div>
</template>

<style scoped></style>
