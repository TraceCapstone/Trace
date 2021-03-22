module.exports = {
  purge: [],
  darkMode: false, // or 'media' or 'class'
  theme: {
    textColor: theme => theme('colors'),
    textColor: {
      'location': '#F5CB5C',
      'job-title': '#3C6E71',
      'placeholder': '#D9D9D9',
      'button': '#FFFFFF',
      'link': '#D9D9D9',
      'link-state-hvr': '#0F4C5C',
      'link-state-actv': '#FFFFFF',
      'link-state-vstd': '#3C6E71',
      'link-state-crrnt': '#F5CB5C',
      backgroundColor: theme => ({
        'login': '#F5CB5C',
        'submit': '#F5CB5C',
        'edit': '#284B63',
        'save': '#284B63',
        'delete': '#333533',
        'cancel': '#333533',
        'bground': '#284B63',
      }),
      extend: {
        zIndex: {
          '-10': '-10',
        }
      },
    },
    variants: {
      extend: {},
    },
    plugins: [],
  },
}
