<script setup>
defineProps({ article: Object });
</script>

<template>
  <div class="box-container">
    <div id="box">
      <!-- <img class="img" :src="`` + article.imagePath" alt="" /> -->
      <img
        class="img"
        :src="article.imagePath"
        alt="C:\triends\triends-front\src\assets\savedImage\498a4e87-dc3a-48fb-9977-0ebf2ab95242.jpg"
      />
      <h1 class="heading">{{ article.title }}</h1>
      <div class="data">
        <span class="user-id">{{ article.userName }}</span>
        <span class="date">{{ article.registerTime }}</span>
        <span class="hit">추천수 {{ article.like }}</span>
        <span
          ><a href="#" class="more"><span class="ir">더보기</span></a></span
        >
      </div>
      <p class="texts">
        {{ article.content }}
      </p>
    </div>
  </div>
</template>

<style scoped>
.box-container {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

#box {
  margin: 10px;
  width: 300px;
  border-radius: 8px;
  overflow: hidden;
  margin: 20px;
  transition: all 0.3s cubic-bezier(0.42, 0, 0.58, 1);
}

#box:hover {
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  transform: translateY(-10px);
}

#box * {
  padding: 10px;
}

#box .img {
  display: block;
  width: 100%;
  padding: 0;
}

#box .heading {
  font-size: 28px;
}

#box .data {
  display: flex;
  flex-direction: column;
  font-size: 12px;
  color: #666;
}

#box .data span {
  padding: 0;
}

#box .data .date {
  margin-bottom: 2px;
}

#box .data .user-id {
  font-size: 16px;
  color: #000;
  font-weight: 600;
}

#box .texts {
  font-size: 14px;
  line-height: 18px;
}
</style>
