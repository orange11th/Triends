<script setup></script>

<template>
  <div class="container">
    <!-- class carousel-fade 지우면 슬라이드쇼로 바뀜 -->
    <div id="carouselExampleCaptions" class="carousel slide carousel-fade" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
          aria-label="Slide 4"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4"
          aria-label="Slide 5"></button>
        </div>
        <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="@/assets/img/slide/1.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>First slide label</h5>
            <p>Some representative placeholder content for the first slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/img/slide/2.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Second slide label</h5>
            <p>Some representative placeholder content for the second slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/img/slide/3.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Third slide label</h5>
            <p>Some representative placeholder content for the third slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/img/slide/4.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Forth slide label</h5>
            <p>Some representative placeholder content for the Forth slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/img/slide/5.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>Fifth slide label</h5>
            <p>Some representative placeholder content for the Fifth slide.</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="row">
    <div class="col-md-4">
      <h3>Triends란?</h3>
      <RouterLink :to="{ name: 'about' }"><img src="@/assets/img/slide/5.jpg" class="img-fluid" alt="Image 1"></RouterLink>
    </div>
    <div class="col-md-4">
      <h3>여행지 검색</h3>
      <RouterLink :to="{ name: 'attraction-list' }"><img src="@/assets/img/slide/5.jpg" class="img-fluid" alt="Image 2"></RouterLink><br />
    </div>
    <div class="col-md-4">
      <h3>여행 후기</h3>
      <RouterLink :to="{ name: 'board' }"><img src="@/assets/img/slide/5.jpg" class="img-fluid" alt="Image 3"></RouterLink><br />
    </div>
  </div>
  </div>
</template>
  
<style scoped>
.carousel-item {
     border: 5px solid black;
}

.row{
  margin: 20px 0;
}

.img-fluid{
  border: 3px solid black;
}

.img-fluid:hover {
  transform: scale(1.05);
}
</style>
