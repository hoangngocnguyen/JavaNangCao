<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

    <style type="text/tailwindcss">
        @theme {
        --color-clifford: #da373d;
      }
    </style>

    <style>
        .line {
            background: #ededed;
            height: 1px;
            flex: 1 0 auto;
        }

        .divider {
            display: flex;
            align-items: center;
        }

        .divider2 {
            display: flex;
            align-items: center;
        }

        .divider2 span {
            margin-right: 8px;
        }

        .divider span {
            flex: .2 0 auto;
            text-align: center;
        }

        a {
            text-decoration: none;
            color: inherit;
        }

        #eye {
            width: 16px;
        }
        #eye #eye-close {
            display: none;
        }

        #eye.open #eye-close {
            display: block;
        }
        #eye.open #eye-open {
           display: none;
        }

        #eye{
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
        }
    </style>

</head>

<body>
    <div class="bg-white w-[400px] shadow-lg mx-auto px-[40px] pb-5">
        <h1 class="text-center !text-[22px] py-2 font-semibold">Đăng nhập</h1>
        <a href="#" class="flex items-center border-gray-500 border-1 rounded-[8px] mb-[12px] py-[12px] px-[20px]">
            <svg fill="currentColor" stroke="" viewBox="0 0 16 17" width="20" height="20"
                xmlns="http://www.w3.org/2000/svg">
                <g clip-path="url(#clip0_2635_44439)">
                    <path
                        d="M6.02293 16.5L6 9.5H3V6.5H6V4.5C6 1.8008 7.67151 0.5 10.0794 0.5C11.2328 0.5 12.2241 0.58587 12.5129 0.62425V3.44507L10.843 3.44583C9.53343 3.44583 9.27987 4.0681 9.27987 4.98124V6.5H13L12 9.5H9.27986V16.5H6.02293Z">
                    </path>
                </g>
                <defs>
                    <clipPath id="clip0_2635_44439">
                        <rect width="16" height="16" fill="white" transform="translate(0 0.5)"></rect>
                    </clipPath>
                </defs>
            </svg>
            <span class="text-[18px] ml-3">Đăng nhập với Google</span>
        </a>
        <a href="#" class="flex items-center border-gray-500 border-1 rounded-[8px] mb-[12px] py-[12px] px-[20px]">
            <svg fill="currentColor" stroke="" viewBox="0 0 16 17" width="20" height="20"
                xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M7.99991 4.16655C9.01809 4.16592 10.0036 4.52573 10.7819 5.18221L12.9619 3.10621C12.1251 2.33457 11.1199 1.76861 10.0261 1.45323C8.9324 1.13785 7.7802 1.08175 6.66099 1.28936C5.54178 1.49697 4.48636 1.96259 3.5785 2.64927C2.67064 3.33594 1.93532 4.22477 1.43091 5.24521L3.88858 7.13955C4.1752 6.27524 4.72649 5.52302 5.46438 4.98944C6.20226 4.45586 7.08932 4.16797 7.99991 4.16655Z"
                    fill="#D94F3D"></path>
                <path
                    d="M3.66662 8.49978C3.66724 8.03737 3.74219 7.57807 3.88862 7.13945L1.43096 5.24512C0.928241 6.25642 0.666626 7.37042 0.666626 8.49978C0.666626 9.62914 0.928241 10.7431 1.43096 11.7545L3.88862 9.86012C3.74219 9.4215 3.66724 8.9622 3.66662 8.49978Z"
                    fill="#F2C042"></path>
                <path
                    d="M15.0333 7.1665H8.03333V10.1665H12C11.764 11.0148 11.2234 11.7461 10.4817 12.2205L12.9203 14.1005C14.4787 12.7018 15.3943 10.4275 15.0333 7.1665Z"
                    fill="#5085ED"></path>
                <path
                    d="M10.4806 12.2204C9.72692 12.6518 8.86773 12.864 7.99991 12.8331C7.08932 12.8317 6.20226 12.5438 5.46438 12.0102C4.72649 11.4766 4.1752 10.7244 3.88858 9.86011L1.43091 11.7544C2.03796 12.9781 2.97439 14.0082 4.13489 14.7287C5.29539 15.4493 6.63391 15.8318 7.99991 15.8331C9.79736 15.8819 11.5494 15.2646 12.9192 14.0998L10.4806 12.2204Z">
                </path>
            </svg>
            <span class="text-[18px] ml-3">Đăng nhập với Facebook</span>
        </a>

        <div class="divider">
            <div class="line"></div>

            <span>OR</span>
            <div class="line"></div>

        </div>

        <form action="" class="mt-2">
            <div class="mb-2">
                <input type="email" required class="form-control" id="exampleFormControlInput1" placeholder="Email">
            </div>
            <div class="mb-2 relative">
                <input type="password" required class="form-control" id="passwordInput" placeholder="Mật khẩu">
                <div id="eye" onclick="togglePassword(this, 'passwordInput')">
                    <div id="eye-open">
                    <svg viewBox="0 0 488.85 488.85" className="w-18">
                        <g>
                            <path
                                d="M244.425 98.725c-93.4 0-178.1 51.1-240.6 134.1-5.1 6.8-5.1 16.3 0 23.1 62.5 83.1 147.2 134.2 240.6 134.2s178.1-51.1 240.6-134.1c5.1-6.8 5.1-16.3 0-23.1-62.5-83.1-147.2-134.2-240.6-134.2zm6.7 248.3c-62 3.9-113.2-47.2-109.3-109.3 3.2-51.2 44.7-92.7 95.9-95.9 62-3.9 113.2 47.2 109.3 109.3-3.3 51.1-44.8 92.6-95.9 95.9zm-3.1-47.4c-33.4 2.1-61-25.4-58.8-58.8 1.7-27.6 24.1-49.9 51.7-51.7 33.4-2.1 61 25.4 58.8 58.8-1.8 27.7-24.2 50-51.7 51.7z"
                                fill="#000000" opacity="1" data-original="#000000" class="">
                            </path>
                        </g>
                    </svg>
                </div>
                <div id="eye-close">
                    <svg viewBox="0 0 512 512" className="w-18">
                        <g>
                            <g data-name="Layer 16">
                                <path
                                    d="M419.72 419.72 92.26 92.27l-.07-.08a19 19 0 0 0-26.78 27l28.67 28.67a332.64 332.64 0 0 0-88.19 89 34.22 34.22 0 0 0 0 38.38C65.86 364 160.93 416 256 415.35a271.6 271.6 0 0 0 90.46-15.16l46.41 46.41a19 19 0 0 0 26.94-26.79zM256 363.74a107.78 107.78 0 0 1-98.17-152.18l29.95 29.95a69.75 69.75 0 0 0 82.71 82.71l29.95 29.95a107.23 107.23 0 0 1-44.44 9.57zM506.11 236.81C446.14 148 351.07 96 256 96.65a271.6 271.6 0 0 0-90.46 15.16l46 46a107.78 107.78 0 0 1 142.63 142.63l63.74 63.74a332.49 332.49 0 0 0 88.2-89 34.22 34.22 0 0 0 0-38.37z"
                                    fill="#000000" opacity="1" data-original="#000000" class=""></path>
                                <path
                                    d="M256 186.26a69.91 69.91 0 0 0-14.49 1.52l82.71 82.7A69.74 69.74 0 0 0 256 186.26z"
                                    fill="#000000" opacity="1" data-original="#000000" class="">
                                </path>
                            </g>
                        </g>
                    </svg>

                </div>
                </div>
            </div>
            <div>
                <a href="#">Quên mật khẩu ?</a>
            </div>

            <button type="submit" class="btn w-full mt-4 py-3 !font-bold text-white !bg-[#098642]">Tiếp tục</button>

            <div class="mt-2">
                <a href="dangky.jsp">
                    <span class="text-gray-500">Bạn chưa có tài khoản?</span> <span href="#"
                        class="font-normal text-[#000] hover:text-[#b6020f]">Đăng ký ngay</span>

                </a>
            </div>

            <div class="divider2 mt-5">
                <span>Tài khoản áp dụng cho</span>
                <div class="line"></div>
            </div>

            <div class="flex mt-3 justify-center gap-4">
                <a href="https://tailieu.vn/" class="w-[100px]"><img
                        src="	https://tailieu.vn/images/logo_tailieu.vn2.png" alt=""></a>
                <a href="https://tracnghiem.net/" class="w-[100px]"><img
                        src="	https://s.tracnghiem.net/assets/images/header/trac-nghiem-online.png" alt=""></a>
            </div>
        </form>
    </div>

</body>
<script>
    const togglePassword = (eyeIcon, idInput) => {
        const passwordInput = document.getElementById(idInput)
        console.log(passwordInput.type);
        eyeIcon.classList.toggle("open")
        passwordInput.type = passwordInput.type === "text" ? "password" : "text";
    }
</script>

</html>