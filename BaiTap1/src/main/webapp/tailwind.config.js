tailwind.config = {
  content: ["./*.{html,js}", "./src/**/*.{html,js,jsp}"],
  theme: {
    extend: {
        container: {
            screen: {
                'sm': '640px',
                'md': '768px',
                'lg': '1024px',
                'xl': '1200px',     // Fix lại cho đúng với khung của Bootstrap
                '2xl': '1536px',
            }
        }
    },
  },
  plugins: [],
}

console.log("Đã thêm tailwind.config.js");
