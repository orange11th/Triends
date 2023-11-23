<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import "@/assets/css/main.css";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

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
</script>

<template>
  <div>
    <!-- Hero Image, text & button -->

    <div id="hero-header">
      <div id="hero-content">
        <h1 id="hero-title">A RANDOM TITLE</h1>

        <p id="hero-text">Some random text to describe the random title</p>

        <a href="#presentation-container"
          ><button id="hero-button">Next Section</button></a
        >
      </div>
    </div>

    <div id="main">
      <!-- Presentation Section -->

      <div id="presentation-container">
        <div id="presentation-title-description">
          <h1>Here is how <span id="red-text">we do it</span></h1>

          <hr id="title-text-separator" />

          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </p>
        </div>

        <div id="section-buttons">
          <button class="product-button" id="first-product-button">
            Product 1
          </button>

          <button class="product-button" id="second-product-button">
            Product 2
          </button>

          <button class="product-button" id="third-product-button">
            Product 3
          </button>

          <button class="product-button" id="fourth-product-button">
            Product 4
          </button>
        </div>

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

            </div>
          </div>
        </div>
      </div>

      <!-- "Features Section" -->

      <div id="features-container">
        <!-- Features Section Description -->
        <div id="features-description">
          <h1>
            Our services offer <span id="red-text"> the best features </span>
          </h1>

          <hr id="title-text-separator" />

          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </p>
        </div>

        <!-- Features of the Services -->
        <div id="service-features">
          <div id="feature">
            <img
              src="https://i.ibb.co/tQrPJGk/like.png"
              alt="Heart Icon"
              id="heart-icon"
            />

            <h2 id="feature-title">Short Title</h2>

            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

            <a href="#"><button id="feature-button">See more!</button></a>
          </div>

          <div id="feature">
            <img
              src="https://i.ibb.co/LY0pq5X/web-programming.png"
              alt="Code Icon"
              id="code-icon"
            />

            <h2 id="feature-title">Short Title</h2>

            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

            <a href="#"><button id="feature-button">See more!</button></a>
          </div>

          <div id="feature">
            <img
              src="https://i.ibb.co/vHjwX9N/smartphone-with-shield.png"
              alt="Shield on Phone Icon"
              id="phone-icon"
            />

            <h2 id="feature-title">Short Title</h2>

            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>

            <a href="#"><button id="feature-button">See more!</button></a>
          </div>
        </div>
      </div>
      <!-- /"Features Section" -->

      <!-- Newsletter Section -->
      <div id="newsletter-container">
        <div id="newsletter-title-description">
          <h1>Join our Newsletter!</h1>

          <hr id="title-text-separator" />
        </div>

        <div id="email-input-container">
          <form
            id="email-form"
            action="https://www.freecodecamp.com/email-submit"
          >
            <label> Join our Newsletter and don't miss future deals! </label>
            <button id="submit" type="submit">Subscribe</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
