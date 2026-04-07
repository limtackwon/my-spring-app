<template>
  <!-- ── Navbar ── -->
  <v-app-bar flat color="white" elevation="1" height="68">
    <v-container class="d-flex align-center py-0">
      <div class="d-flex align-center">
        <v-icon color="primary" size="28" class="mr-2">mdi-leaf</v-icon>
        <span class="text-primary font-weight-bold text-h6">GreenHope</span>
        <span class="text-grey-darken-2 ml-1 text-body-2">사회공헌</span>
      </div>
      <v-spacer />
      <div class="d-none d-sm-flex align-center">
        <v-btn variant="text" color="grey-darken-2" class="text-body-2">활동 소개</v-btn>
        <v-btn variant="text" color="grey-darken-2" class="text-body-2">투명성 보고</v-btn>
        <v-btn
          color="primary"
          variant="flat"
          rounded="lg"
          class="ml-3"
          elevation="0"
          @click="scrollTo('#volunteer')"
        >
          참여하기
        </v-btn>
      </div>
    </v-container>
  </v-app-bar>

  <!-- ── Hero Section ── -->
  <section class="hero-section" id="hero">
    <div class="hero-overlay">
      <v-container class="h-100 d-flex flex-column justify-center align-center text-center px-4">
        <div class="hero-text-box">
          <v-chip
            color="white"
            variant="flat"
            class="mb-6 text-primary font-weight-medium"
            size="small"
            prepend-icon="mdi-leaf"
          >
            함께하는 변화
          </v-chip>

          <h1 class="hero-title text-white">
            함께 만드는<br />더 나은 세상
          </h1>

          <p class="hero-subtitle text-white mt-5">
            우리의 작은 나눔이 모여 큰 변화를 만들어냅니다.<br />
            지금 바로 함께해 주세요.
          </p>
          <div class="d-flex flex-wrap gap-4 mt-10 justify-center">
            <v-btn
              color="primary"
              size="x-large"
              rounded="lg"
              elevation="0"
              class="px-10 font-weight-bold"
              prepend-icon="mdi-hand-heart"
              @click="scrollTo('#volunteer')"
            >
              참여하기
            </v-btn>
            <v-btn
              variant="outlined"
              color="white"
              size="x-large"
              rounded="lg"
              class="px-8"
              @click="scrollTo('#impact')"
            >
              더 알아보기
            </v-btn>
          </div>
        </div>
      </v-container>
    </div>
  </section>

  <!-- ── Impact Counter ── -->
  <section class="py-16 bg-white" id="impact">
    <v-container>
      <div class="text-center mb-12">
        <p class="text-primary font-weight-medium text-body-2 mb-2 text-uppercase letter-spacing-2">
          Impact Dashboard
        </p>
        <h2 class="text-h4 font-weight-bold text-grey-darken-3">우리가 함께 만든 변화</h2>
        <p class="text-body-1 text-grey mt-2">숫자로 보는 실시간 사회공헌 현황</p>
        <div class="divider-line mx-auto mt-4" />
      </div>

      <v-row justify="center" class="ga-2">
        <v-col
          v-for="stat in impactStats"
          :key="stat.label"
          cols="12"
          sm="6"
          md="4"
        >
          <v-card rounded="xl" elevation="0" border class="pa-8 text-center stat-card h-100">
            <v-avatar :color="stat.bgColor" size="72" class="mb-5">
              <v-icon :color="stat.color" size="36">{{ stat.icon }}</v-icon>
            </v-avatar>
            <div class="text-h3 font-weight-bold mb-1" :style="`color: ${stat.color}`">
              {{ stat.displayValue }}
            </div>
            <div class="text-body-1 font-weight-medium text-grey-darken-2 mb-1">
              {{ stat.label }}
            </div>
            <div class="text-caption text-grey">{{ stat.description }}</div>
            <v-divider class="my-4" />
            <div class="d-flex align-center justify-center">
              <v-icon color="success" size="16" class="mr-1">mdi-trending-up</v-icon>
              <span class="text-caption text-success font-weight-medium">{{ stat.growth }}</span>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </section>

  <!-- ── Recent Activity Cards (v-slide-group) ── -->
  <section class="py-16 section-soft" id="activities">
    <v-container>
      <div class="text-center mb-12">
        <p class="text-primary font-weight-medium text-body-2 mb-2 text-uppercase letter-spacing-2">
          Recent Activities
        </p>
        <h2 class="text-h4 font-weight-bold text-grey-darken-3">최근 사회공헌 활동</h2>
        <p class="text-body-1 text-grey mt-2">현재 진행 중인 활동에 함께해 주세요</p>
        <div class="divider-line mx-auto mt-4" />
      </div>

      <v-slide-group show-arrows center-active class="pa-2">
        <v-slide-group-item v-for="activity in activities" :key="activity.id">
          <v-card
            rounded="xl"
            elevation="0"
            width="320"
            class="ma-3 activity-card"
          >
            <!-- Placeholder image -->
            <v-sheet
              :color="activity.bgColor"
              height="200"
              rounded="t-xl"
              class="d-flex align-center justify-center position-relative"
            >
              <v-icon :color="activity.iconColor" size="72">{{ activity.icon }}</v-icon>
              <v-chip
                :color="activity.statusColor"
                size="small"
                class="position-absolute"
                style="top: 12px; left: 12px;"
                variant="flat"
              >
                {{ activity.status }}
              </v-chip>
            </v-sheet>

            <v-card-item class="pa-6">
              <v-card-title class="text-body-1 font-weight-bold text-grey-darken-3 text-wrap pb-1">
                {{ activity.title }}
              </v-card-title>
              <v-card-subtitle class="text-wrap text-grey" style="white-space: normal;">
                {{ activity.description }}
              </v-card-subtitle>
            </v-card-item>

            <v-card-actions class="px-6 pb-6 pt-0">
              <v-chip size="small" prepend-icon="mdi-calendar-outline" variant="tonal" color="grey-darken-1">
                {{ activity.date }}
              </v-chip>
              <v-spacer />
              <v-chip size="small" prepend-icon="mdi-account-group" variant="tonal" color="primary">
                {{ activity.participants.toLocaleString() }}명 참여
              </v-chip>
            </v-card-actions>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-container>
  </section>

  <!-- ── Transparency / Donation Table ── -->
  <section class="py-16 bg-white" id="transparency">
    <v-container>
      <div class="text-center mb-12">
        <p class="text-primary font-weight-medium text-body-2 mb-2 text-uppercase letter-spacing-2">
          Transparency
        </p>
        <h2 class="text-h4 font-weight-bold text-grey-darken-3">기부금 집행내역</h2>
        <p class="text-body-1 text-grey mt-2">투명한 기부금 사용 내역을 공개합니다</p>
        <div class="divider-line mx-auto mt-4" />
      </div>

      <v-card rounded="xl" elevation="0" border>
        <v-card-title class="pa-6 pb-0 d-flex align-center">
          <v-icon color="primary" class="mr-2">mdi-shield-check</v-icon>
          <span class="text-body-1 font-weight-medium">2025년 기부금 집행 현황</span>
          <v-spacer />
          <v-btn
            variant="outlined"
            color="primary"
            size="small"
            rounded="lg"
            prepend-icon="mdi-download"
          >
            보고서 다운로드
          </v-btn>
        </v-card-title>

        <v-data-table
          :headers="donationHeaders"
          :items="donationRecords"
          :loading="loadingDonations"
          items-per-page="5"
          class="px-4"
        >
          <template #item.amount="{ item }">
            <span class="font-weight-medium text-primary">
              {{ formatCurrency(item.amount) }}
            </span>
          </template>
          <template #item.status="{ item }">
            <v-chip
              :color="statusColor(item.status)"
              size="small"
              variant="tonal"
            >
              {{ item.status }}
            </v-chip>
          </template>
          <template #item.category="{ item }">
            <v-chip size="small" variant="outlined" color="grey-darken-1">
              {{ item.category }}
            </v-chip>
          </template>
          <template #bottom>
            <div class="pa-4 d-flex align-center">
              <v-icon color="grey" size="16" class="mr-1">mdi-information-outline</v-icon>
              <span class="text-caption text-grey">데이터는 실시간으로 불러옵니다.</span>
            </div>
          </template>
        </v-data-table>
      </v-card>
    </v-container>
  </section>

  <!-- ── Volunteer Form ── -->
  <section class="py-16 section-hero-green" id="volunteer">
    <v-container>
      <v-row justify="center">
        <v-col cols="12" md="7" lg="6">
          <div class="text-center mb-10">
            <v-icon color="white" size="48" class="mb-4">mdi-hand-heart</v-icon>
            <h2 class="text-h4 font-weight-bold text-white">자원봉사 신청</h2>
            <p class="text-white mt-2" style="opacity: 0.88;">
              소중한 시간을 나눠 더 나은 세상을 만들어 주세요
            </p>
          </div>

          <v-card rounded="xl" elevation="0" class="pa-8">
            <v-form ref="volunteerForm" @submit.prevent="submitVolunteer">
              <v-row>
                <v-col cols="12" sm="6">
                  <v-text-field
                    v-model="form.name"
                    label="이름"
                    prepend-inner-icon="mdi-account-outline"
                    variant="outlined"
                    rounded="lg"
                    color="primary"
                    :rules="[v => !!v || '이름을 입력해주세요']"
                  />
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field
                    v-model="form.phone"
                    label="연락처"
                    prepend-inner-icon="mdi-phone-outline"
                    variant="outlined"
                    rounded="lg"
                    color="primary"
                    placeholder="010-0000-0000"
                    :rules="[v => !!v || '연락처를 입력해주세요']"
                  />
                </v-col>
                <v-col cols="12">
                  <v-select
                    v-model="form.activity"
                    :items="activityOptions"
                    label="참여하고 싶은 활동"
                    prepend-inner-icon="mdi-format-list-bulleted"
                    variant="outlined"
                    rounded="lg"
                    color="primary"
                    :rules="[v => !!v || '활동을 선택해주세요']"
                  />
                </v-col>
                <v-col cols="12">
                  <v-textarea
                    v-model="form.message"
                    label="한마디"
                    prepend-inner-icon="mdi-message-text-outline"
                    variant="outlined"
                    rounded="lg"
                    color="primary"
                    rows="3"
                    placeholder="봉사 신청 동기나 하고 싶은 말씀을 적어주세요 (선택)"
                  />
                </v-col>
                <v-col cols="12">
                  <v-btn
                    type="submit"
                    color="primary"
                    block
                    size="large"
                    rounded="lg"
                    elevation="0"
                    :loading="submitting"
                    prepend-icon="mdi-send"
                    class="font-weight-bold"
                  >
                    신청하기
                  </v-btn>
                </v-col>
              </v-row>
            </v-form>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </section>

  <!-- ── Footer ── -->
  <v-footer color="grey-darken-4" class="py-10">
    <v-container>
      <v-row align="center">
        <v-col cols="12" md="5">
          <div class="d-flex align-center mb-3">
            <v-icon color="primary" size="24" class="mr-2">mdi-leaf</v-icon>
            <span class="text-white font-weight-bold text-h6">GreenHope</span>
            <span class="text-grey ml-1">사회공헌</span>
          </div>
          <p class="text-grey text-body-2 mb-0" style="line-height: 1.8;">
            함께하는 나눔으로 더 나은 세상을 만들어갑니다.<br />
            작은 관심이 큰 변화의 시작입니다.
          </p>
        </v-col>
        <v-col cols="12" md="7" class="text-md-right">
          <div class="mb-2">
            <v-btn variant="text" color="grey" size="small">개인정보처리방침</v-btn>
            <v-btn variant="text" color="grey" size="small">이용약관</v-btn>
            <v-btn variant="text" color="grey" size="small">Contact</v-btn>
          </div>
          <p class="text-grey text-caption mb-0">
            © 2026 GreenHope Corp. All rights reserved.
          </p>
        </v-col>
      </v-row>
    </v-container>
  </v-footer>

  <!-- Snackbar -->
  <v-snackbar v-model="snackbar.show" :color="snackbar.color" rounded="lg" location="top">
    {{ snackbar.message }}
    <template #actions>
      <v-btn variant="text" @click="snackbar.show = false">닫기</v-btn>
    </template>
  </v-snackbar>
</template>

<script setup>
import { ref, onMounted } from 'vue'

// ── Scroll helper ──
const scrollTo = (selector) => {
  document.querySelector(selector)?.scrollIntoView({ behavior: 'smooth' })
}

// ── Impact Stats ──
const impactStats = ref([
  {
    label: '총 기부금',
    displayValue: '₩24.5억',
    icon: 'mdi-currency-krw',
    color: '#4CAF50',
    bgColor: '#E8F5E9',
    description: '2025년 누적 기부금 총액',
    growth: '전년 대비 18% 증가',
  },
  {
    label: '함께한 봉사자',
    displayValue: '12,500명',
    icon: 'mdi-account-group',
    color: '#1976D2',
    bgColor: '#E3F2FD',
    description: '지금까지 함께한 소중한 분들',
    growth: '전년 대비 23% 증가',
  },
  {
    label: '지원한 아동 수',
    displayValue: '3,200명',
    icon: 'mdi-human-child',
    color: '#F57C00',
    bgColor: '#FFF3E0',
    description: '교육 및 생활 지원 아동',
    growth: '전년 대비 11% 증가',
  },
])

// ── Activities ──
const activities = ref([
  {
    id: 1,
    title: '농촌 일손 돕기 봉사',
    description: '고령화로 어려움을 겪는 농촌 마을의 수확을 함께 도와드립니다.',
    date: '2025.05.10',
    participants: 48,
    status: '모집중',
    statusColor: 'success',
    bgColor: '#E8F5E9',
    icon: 'mdi-sprout',
    iconColor: '#388E3C',
  },
  {
    id: 2,
    title: '아동 교육 멘토링',
    description: '지역 아동센터 아이들과 함께하는 학습 멘토링 프로그램입니다.',
    date: '2025.05.17',
    participants: 23,
    status: '진행중',
    statusColor: 'primary',
    bgColor: '#E3F2FD',
    icon: 'mdi-book-open-variant',
    iconColor: '#1565C0',
  },
  {
    id: 3,
    title: '환경 정화 캠페인',
    description: '한강변 및 도심 공원 환경 정화 활동으로 깨끗한 환경을 만들어갑니다.',
    date: '2025.05.24',
    participants: 67,
    status: '모집중',
    statusColor: 'success',
    bgColor: '#E0F2F1',
    icon: 'mdi-recycle',
    iconColor: '#00695C',
  },
])

// ── Donation Table ──
const donationHeaders = [
  { title: '집행일', key: 'date', sortable: true, width: '120px' },
  { title: '수혜처', key: 'recipient' },
  { title: '분류', key: 'category', width: '120px' },
  { title: '집행금액', key: 'amount', sortable: true, width: '150px' },
  { title: '상태', key: 'status', width: '100px' },
]

const loadingDonations = ref(false)
const donationRecords = ref([])

const MOCK_DONATIONS = [
  { date: '2025-04-15', recipient: '초록우산 어린이재단', category: '아동교육', amount: 50000000, status: '집행완료' },
  { date: '2025-04-01', recipient: '굿네이버스', category: '식품지원', amount: 30000000, status: '집행완료' },
  { date: '2025-03-20', recipient: '사랑의열매', category: '의료지원', amount: 20000000, status: '집행완료' },
  { date: '2025-03-05', recipient: '지역아동센터연합', category: '교육환경', amount: 15000000, status: '집행완료' },
  { date: '2025-05-01', recipient: '환경재단', category: '환경보호', amount: 10000000, status: '진행중' },
  { date: '2025-05-10', recipient: '노인복지관협회', category: '노인복지', amount: 8000000, status: '예정' },
]

const formatCurrency = (val) => '₩' + Number(val).toLocaleString('ko-KR')

const statusColor = (status) => {
  if (status === '집행완료') return 'success'
  if (status === '진행중') return 'primary'
  return 'warning'
}

const loadDonationRecords = async () => {
  loadingDonations.value = true
  try {
    const res = await fetch('/api/v1/payments')
    if (!res.ok) throw new Error('API error')
    donationRecords.value = await res.json()
  } catch {
    donationRecords.value = MOCK_DONATIONS
  } finally {
    loadingDonations.value = false
  }
}

// ── Volunteer Form ──
const volunteerForm = ref(null)
const submitting = ref(false)
const form = ref({ name: '', phone: '', activity: '', message: '' })
const activityOptions = [
  '농촌 일손 돕기',
  '아동 교육 멘토링',
  '환경 정화 캠페인',
  '독거노인 돌봄',
]
const snackbar = ref({ show: false, message: '', color: 'success' })

const submitVolunteer = async () => {
  const { valid } = await volunteerForm.value.validate()
  if (!valid) return

  submitting.value = true
  try {
    const res = await fetch('/api/csr/volunteer', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form.value),
    })
    if (!res.ok) throw new Error()
    snackbar.value = { show: true, message: '신청이 완료되었습니다. 감사합니다!', color: 'success' }
    form.value = { name: '', phone: '', activity: '', message: '' }
    volunteerForm.value.reset()
  } catch {
    snackbar.value = { show: true, message: '신청 중 오류가 발생했습니다. 다시 시도해주세요.', color: 'error' }
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  loadDonationRecords()
})
</script>

<style scoped>
/* ── Hero ── */
.hero-section {
  height: 92vh;
  min-height: 600px;
  background-image:
    linear-gradient(160deg, rgba(0, 0, 0, 0.75) 0%, rgba(20, 70, 20, 0.65) 100%),
    url('https://images.unsplash.com/photo-1559027615-cd4628902d4a?w=1920&q=80');
  background-size: cover;
  background-position: center;
  position: relative;
}

.hero-overlay {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.hero-text-box {
  background: rgba(0, 0, 0, 0.45);
  backdrop-filter: blur(6px);
  -webkit-backdrop-filter: blur(6px);
  border-radius: 20px;
  padding: 48px 56px;
  max-width: 700px;
  width: 100%;
}

.hero-title {
  font-size: clamp(2.4rem, 6vw, 4rem);
  font-weight: 800;
  line-height: 1.2;
  letter-spacing: -0.5px;
  text-shadow: 0 2px 12px rgba(0, 0, 0, 0.6);
}

.hero-subtitle {
  font-size: 1.1rem;
  opacity: 1;
  line-height: 1.9;
  text-shadow: 0 1px 8px rgba(0, 0, 0, 0.7);
}

/* ── Section Backgrounds ── */
.section-soft {
  background-color: #F1F8F1;
}

.section-hero-green {
  background: linear-gradient(135deg, #388E3C 0%, #66BB6A 100%);
}

/* ── Accent Line ── */
.divider-line {
  width: 48px;
  height: 4px;
  background: #4CAF50;
  border-radius: 2px;
}

.letter-spacing-2 {
  letter-spacing: 0.12em;
}

/* ── Stat Cards ── */
.stat-card {
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}
.stat-card:hover {
  transform: translateY(-6px);
  box-shadow: 0 16px 40px rgba(76, 175, 80, 0.18) !important;
}

/* ── Activity Cards ── */
.activity-card {
  border: 1px solid #E8F5E9 !important;
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}
.activity-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.1) !important;
}
</style>
