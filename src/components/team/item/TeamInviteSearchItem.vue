<script setup>
import { ref, computed, watch } from "vue";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";

import { useMemberStore } from "@/stores/member";

import "@/assets/css/team/teamSearch.css";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({
  inviteList: Array,
  team: Object,
});

const input = ref("");
const showResults = ref(false);

// userId와 userName을 결합하여 검색 인덱스를 생성합니다.
const searchIndex = computed(() => {
  return props.inviteList.map((item) => ({
    label: `${item.userId}(${item.userName})`,
    userId: item.userId,
  }));
});

const filteredResults = computed(() => {
  const inputTerms = input.value.toLowerCase().split(" ");
  const searchTerms = inputTerms[inputTerms.length - 1];

  return searchIndex.value.filter((item) =>
    item.label.toLowerCase().includes(searchTerms)
  );
});

const noResults = computed(() => {
  return filteredResults.value.length === 0 && input.value.length > 0;
});

function search() {
  showResults.value = input.value.length > 0;
}

watch(input, () => {
  search();
});

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
</script>

<template>
  <div>
    <div class="search-title">
      <h5>Add Members</h5>
      <span>in</span>
      <h2>{{ team.teamName }}</h2>
    </div>
    <input
      type="text"
      class="search-field"
      v-model="input"
      @keyup="search"
      autoFocus
      placeholder="search by name or ID"
    />
    <ul v-if="showResults" class="term-list">
      <a
        v-for="result in filteredResults"
        :key="result.userId"
        href="#"
        @click.prevent="inviteTeam(props.team.teamId, result.userId)"
      >
        <li>
          <img src="@/assets/img/icon/user.svg" alt="">
          {{ result.label }}
        </li>
      </a>
      <li>
        <a v-if="noResults"> 검색된 유저가 없어요 ㅠㅠ </a>
      </li>
    </ul>
  </div>
</template>

<style scope></style>
