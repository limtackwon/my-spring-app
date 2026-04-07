import { createApp } from 'vue'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import App from './App.vue'

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'csrLight',
    themes: {
      csrLight: {
        dark: false,
        colors: {
          primary: '#4CAF50',
          'primary-darken-1': '#388E3C',
          secondary: '#81C784',
          surface: '#FFFFFF',
          background: '#F9FBF9',
          error: '#F44336',
          info: '#2196F3',
          success: '#4CAF50',
          warning: '#FF9800',
        },
      },
    },
  },
})

const app = createApp(App)
app.use(vuetify)
app.mount('#app')
