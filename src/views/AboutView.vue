<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import "@/assets/css/main.css";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";
const router = useRouter();
// 상태 정의
const isScrolledBeyond50 = ref(false);
const isResponsiveMenuVisible = ref(false);
const activeProduct = ref(1);

// 헤더 이미지 소스 변경을 위한 상태
const headerImageSrc = ref("/src/assets/img/icon/main-logo.svg");
const headerImageSrcBlack = ref("https://i.ibb.co/0jM3ZvH/reverse-black.png");

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  if (window.scrollY > 50) {
    isScrolledBeyond50.value = true;
    headerImageSrc.value = headerImageSrcBlack.value;
  } else {
    isScrolledBeyond50.value = false;
    headerImageSrc.value = "/src/assets/img/icon/main-logo.svg";
  }
};

// 반응형 메뉴 토글
const toggleResponsiveMenu = () => {
  isResponsiveMenuVisible.value = !isResponsiveMenuVisible.value;
};

// 제품 섹션 표시 변경
const showProduct = (productNumber) => {
  activeProduct.value = productNumber;
};

// 스크롤 이벤트 리스너 추가
onMounted(() => {
  window.addEventListener("scroll", handleScroll);
});

// 컴포넌트 언마운트 시 스크롤 이벤트 리스너 제거
onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

const goToMemberRegist = () => {
  router.push({ name: 'member-regist' });
};
</script>

<template>
  <div>
    <header id="header">
      <div id="normal-header">
        <a href="#" id="image-link"
          ><img src="/src/assets/img/icon/main-logo.svg" alt="" id="header-img"
        /></a>

        <nav id="nav-bar">
          <ul>
            <li class="nav-link" id="menu-item">
              <a href="#hero-header" class="menu-link"> Home </a>
            </li>
            <li class="nav-link" id="menu-item">
              <a href="#features-container" class="menu-link"> Features </a>
            </li>
            <li class="nav-link" id="menu-item">
              <a href="#presentation-container" class="menu-link"> Products </a>
            </li>
            <li class="nav-link" id="menu-item">
              <a href="#main-media-container" class="menu-link"> Media </a>
            </li>
            <li class="nav-link" id="menu-item">
              <a href="#prices-section-container" class="menu-link">
                Pricing
              </a>
            </li>
          </ul>
        </nav>
      </div>

      <div id="responsive-header">
        <div id="responsive-logo-button">
          <a href="#" id="image-link"
            ><img
              src="https://i.ibb.co/0jM3ZvH/reverse-black.png"
              alt="Reverse Logo"
              id="header-img-black"
          /></a>
          <button id="image-button-black">
            <img
              src="https://i.ibb.co/yfM3fdZ/dropdown-menu-black.png"
              alt="Dropdown Menu"
              id="dropdown-button-black"
            />
          </button>
        </div>

        <div id="responsive-nav-bar">
          <ul>
            <li class="nav-link" id="responsive-home-item">
              <a href="#hero-header" class="responsive-menu-link"> Home </a>
            </li>
            <li class="nav-link" id="responsive-features-item">
              <a href="#features-container" class="responsive-menu-link">
                Features
              </a>
            </li>
            <li class="nav-link" id="responsive-products-item">
              <a href="#presentation-container" class="responsive-menu-link">
                Products
              </a>
            </li>
            <li class="nav-link" id="responsive-media-item">
              <a href="#main-media-container" class="responsive-menu-link">
                Media
              </a>
            </li>
            <li class="nav-link" id="responsive-pricing-item">
              <a href="#prices-section-container" class="responsive-menu-link">
                Pricing
              </a>
            </li>
          </ul>
        </div>
      </div>
    </header>

    <!-- Hero Image, text & button -->

    <div id="hero-header">
      <div id="hero-content">
        <h1 id="hero-title">즐거운 동행의 시작</h1>

        <p id="hero-text">트렌즈와 함께 여행지를 탐색하고, 계획을 세워보아요</p>

        <a href="#presentation-container"
          ><button id="hero-button">지금 시작하기</button></a
        >
      </div>
    </div>

    <div id="main">
      <!-- Presentation Section -->

      <div id="presentation-container">

      <div id="features-container">
        <div id="features-description">
          <h1>트렌즈와 <span id="red-text">함께</span> 여행을 시작하세요!</h1>
        </div>
        <!-- Features Section Description -->

        <!-- Features of the Services -->
        <div id="service-features">
          <div id="feature">
            <img
              src="https://i.ibb.co/tQrPJGk/like.png"
              alt="Heart Icon"
              id="heart-icon"
            />
            <h2 id="feature-title">여행지 탐색</h2>
            <p>
              트렌즈는 지역, 여행지 종류(관광, 쇼핑, 숙박 등)에 구애받지 않고 여행지를 탐색할 수 있어요. 원하는 곳을 검색해보는 것은 어떨까요?
            </p>
          </div>
          <div id="feature">
            <img
              src="https://i.ibb.co/LY0pq5X/web-programming.png"
              alt="Code Icon"
              id="code-icon"
            />

            <h2 id="feature-title">팀별 계획</h2>

            <p>
              자유롭게 팀을 생성하고, 친구들을 초대할 수 있어요. 
            </p>

          </div>

          <div id="feature">
            <img
              src="https://i.ibb.co/vHjwX9N/smartphone-with-shield.png"
              alt="Shield on Phone Icon"
              id="phone-icon"
            />

            <h2 id="feature-title">여행 후기</h2>

            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

          </div>
        </div>
      </div>
      <!-- /"Features Section" -->

        <!-- Product Content Section -->
        <div id="section-content-container">
          <!-- Content Container 1 -->
          <div id="product-container-one" class="product-content">
            <div id="content-image-container">
              <img
                src="https://i.ibb.co/WyWxQ3f/action-blur-close-up-735911.jpg"
                id="content-image"
                class="image1"
                alt=""
              />
            </div>

            <div id="content-title-text">
              <h1>Product Nr.1 Title</h1>

              <p id="centered-product-description">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu.
                <br /><br />
              </p>

              <div class="product-features-list" id="first-feature-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <button id="content-button">Read More</button>
            </div>
          </div>

          <!-- Content Container 2 -->
          <div id="product-container-two" class="product-content">
            <div id="content-image-container">
              <img
                src="https://i.ibb.co/rp92fYB/blur-business-close-up-1029757.jpg"
                id="content-image"
                class="image2"
                alt=""
              />
            </div>

            <div id="content-title-text">
              <h1>Product Nr.2 Title</h1>

              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu.
                <br /><br />
              </p>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <button id="content-button">Read More</button>
            </div>
          </div>

          <!-- Content Container 3 -->
          <div id="product-container-three" class="product-content">
            <div id="content-image-container">
              <img
                src="https://i.ibb.co/Yj4fGBN/adult-apple-device-business-839465.jpg"
                id="content-image"
                class="image3"
                alt=""
              />
            </div>

            <div id="content-title-text">
              <h1>Product Nr.3 Title</h1>

              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu.
                <br /><br />
              </p>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <button id="content-button">Read More</button>
            </div>
          </div>

          <!-- Content Container 4 -->
          <div id="product-container-four" class="product-content">
            <div id="content-image-container">
              <img
                src="https://i.ibb.co/t3Lnfm6/arrows-blur-close-up-163130.jpg"
                id="content-image"
                class="image2"
                alt=""
              />
            </div>

            <div id="content-title-text">
              <h1>Product Nr.4 Title</h1>

              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu.
                <br /><br />
              </p>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <div class="product-features-list">
                <div id="product-feature-image">
                  <img
                    src="https://i.ibb.co/LzczvS3/correct.png"
                    alt="check-sign"
                    id="correct-icon"
                  />
                </div>

                <div id="inside-features-title-text">
                  <h2>This is test title</h2>

                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                    do eiusmod tempor, incidintum ut labore et dolor magna
                    aliqua.
                  </p>
                </div>
              </div>

              <button id="content-button">Read More</button>
            </div>
          </div>
        </div>
      </div>

     

      <!-- Newsletter Section -->
      <div id="newsletter-container">
        <div id="newsletter-title-description">
          <h1>이제 여행을 떠나볼까요?</h1>

          <!-- <hr id="title-text-separator" /> -->
        </div>

        <div id="final-container">
            <label id="final-text"> 트렌즈와 함께 즐거운 시간을 보내봐요!</label>
            <!-- <input type="email" placeholder="Enter your e-mail" id="email" /> -->
            <button id="final-button" @click="goToMemberRegist">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
