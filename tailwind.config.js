module.exports = {
  purge: [],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {

        textColor: {
          'location': '#F5CB5C',
          'job-title': '#3C6E71',
          'placeholder': '#D9D9D9',
          'button': '#FFFFFF',
          'link': '#808080',
          'link-state-hvr': '#0F4C5C',
          'link-state-actv': '#FFFFFF',
          'link-state-vstd': '#3C6E71',
          'link-state-crrnt': '#F5CB5C'
        },
          backgroundColor: {
            'login': '#F5CB5C',
            'submit': '#F5CB5C',
            'edit': '#284B63',
            'save': '#284B63',
            'delete': '#333533',
            'cancel': '#333533',
            'bground': '#284B63',
            'sign-up': '#0F4C5C',
          },
        zIndex: {
          '-10': '-10',
          '-20': '-20'
        },
      maxHeight: {
        '0': '0',
        '1/4': '25%',
        '1/2': '50%',
        '3/4': '75%',
        '104': '28rem',
        '108': '32rem',
        '110': '33rem',
        '112': '42rem'
      },
      minHeight: {
        '112': '40',
      }
      },

    },
    variants: {
      extend: {},
    },
    plugins: [],

}
