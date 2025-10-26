close all;
image = imread("firewatch480.jpg");
image_gray = im2gray(image);
imwrite(image_gray, strcat('2', filesep, 'image.png'));
global h;
global w;
global N;
[h, w] = size(image_gray);

% Ядро размытия по Гауссу
N = 3;
sigma = (N - 1) / 6;
A = zeros(N, N);
center = (N + 1) / 2;
for i = 1:N
    for j = 1:N
        A(i,j) = exp(-((i - center)^2 + (j - center)^2) / (2 * sigma^2));
    end
end
K_gaussian = A / sum(A, 'all');
[k_gaussian, l_gaussian] = size(K_gaussian); 

% Ядро блочного размытия
K_block = ones(N);
K_block = K_block / sum(K_block, 'all');
[k_block, l_block] = size(K_block); 

% Ядро увеличения резкости
K_sharpen = [0 -1 0; -1 5 -1; 0 -1 0];
[k_sharpen, l_sharpen] = size(K_sharpen); 

% Ядро выделения краев
K_edge = [-1 -1 -1; -1 8 -1; -1 -1 -1];
[k_edge, l_edge] = size(K_edge); 

% Ядро Скарра (раньше тут был Laplacian of Gaussian, поэтому переменная названа так)
K_laplacian = [3 0 -3; 10 0 -10; 3 0 -3];
[k_laplacian, l_laplacian] = size(K_laplacian); 

img_gaussian = conv2(image_gray, K_gaussian);
img_block = conv2(image_gray, K_block);
img_sharpen = conv2(image_gray, K_sharpen);
img_edge = conv2(image_gray, K_edge);
img_laplacian = conv2(image_gray, K_laplacian);

h_g = figure;
imshow(img_gaussian, [],"Border","tight");
h_b = figure;
imshow(img_block, [],"Border","tight");
imwrite(img_sharpen, strcat('2', filesep, 'img_sharpen_by_conv2.png'));
imwrite(img_edge, strcat('2', filesep, 'img_edge_by_conv2.png'));
imwrite(img_laplacian, strcat('2', filesep, 'img_laplacian_by_conv2.png'));
saveas(h_g, strcat('2', filesep, num2str(N), '_img_gaussian_by_conv2', '.png'), 'png')
saveas(h_b, strcat('2', filesep, num2str(N), '_img_block_by_conv2', '.png'), 'png')

% Логарифмы модулей обазов

function abs_fourier_log_norm = ln_mod_fourier(data, k, l, arg_name)
    global h;
    global w;
    global N;
    fourier = fftshift(fft2(data, h+k-1,w+l-1));
    abs_fourier = abs(fourier);
    abs_fourier_log = log(abs_fourier + 1);
    abs_fourier_log_norm = abs_fourier_log./max(abs_fourier_log(:), [], 'all');
    h1 = figure;
    imshow(abs_fourier_log_norm, [],"Border","tight");
    if (k == 3)
        saveas(h1, strcat('2', filesep, 'abs_fourier_log_norm_', arg_name, '.png'), 'png');
    else
        saveas(h1, strcat('2', filesep, num2str(k), '_abs_fourier_log_norm_', arg_name, '.png'), 'png');
    end
end
ln_mod_fourier(image_gray, 3, 3, 'image');
ln_mod_fourier(K_gaussian, N, N, 'gaussian');
ln_mod_fourier(K_block, N, N, 'block');
ln_mod_fourier(K_sharpen, 3, 3, 'sharpen');
ln_mod_fourier(K_edge, 3, 3, 'edge');
ln_mod_fourier(K_laplacian, 3, 3, 'laplacian');

% Фурье-образы

fourier_image = fft2(image_gray,h+k_sharpen-1,w+l_sharpen-1);
fourier_image_n = fft2(image_gray,h+k_gaussian-1,w+l_gaussian-1);

fourier_gaussian = fft2(K_gaussian,h+k_gaussian-1,w+l_gaussian-1);
fourier_block = fft2(K_block,h+k_block-1,w+l_block-1);
fourier_sharpen = fft2(K_sharpen,h+k_sharpen-1,w+l_sharpen-1);
fourier_edge = fft2(K_edge,h+k_edge-1,w+l_edge-1);
fourier_laplacian = fft2(K_laplacian,h+k_laplacian-1,w+l_laplacian-1);

% Умножение образа изображения на образы ядер

img_gaussian_fourier = fourier_image_n .* fourier_gaussian;
img_block_fourier = fourier_image_n .* fourier_block;
img_sharpen_fourier = fourier_image .* fourier_sharpen;
img_edge_fourier = fourier_image .* fourier_edge;
img_laplacian_fourier = fourier_image .* fourier_laplacian;

% Обратные преобразования Фурье от полученных произведений

img_gaussian1 = real(ifft2(img_gaussian_fourier));
img_block1 = ifft2(img_block_fourier);
img_sharpen1 = real(ifft2(img_sharpen_fourier));
img_edge1 = ifft2(img_edge_fourier);
img_laplacian1 = ifft2(img_laplacian_fourier);

% Сохранение логарифмов модулей Фурье-образов получившихся изображений (сначала conv2, затем фурье)

ln_mod_fourier(img_gaussian, N, N, 'img_gaussian');
ln_mod_fourier(img_block, N, N, 'img_block');
ln_mod_fourier(img_sharpen, 3, 3, 'img_sharpen');
ln_mod_fourier(img_edge, 3, 3, 'img_edge');
ln_mod_fourier(img_laplacian, 3, 3, 'img_laplacian');

ln_mod_fourier(img_gaussian1, N, N, 'img_gaussian1');
ln_mod_fourier(img_block1, N, N, 'img_block1');
ln_mod_fourier(img_sharpen1, 3, 3, 'img_sharpen1');
ln_mod_fourier(img_edge1, 3, 3, 'img_edge1');
ln_mod_fourier(img_laplacian1, 3, 3, 'img_laplacian1');

% Отображение полученных изображений

h_g1 = figure;
imshow(img_gaussian1, [],"Border","tight");
h_b1 = figure;
imshow(img_block1, [],"Border","tight");
imwrite(img_sharpen1, strcat('2', filesep, 'img_sharpen_by_fourier.png'))
imwrite(img_edge1, strcat('2', filesep, 'img_edge_by_fourier.png'))
imwrite(img_laplacian1, strcat('2', filesep, 'img_laplacian_by_fourier.png'))
saveas(h_g1, strcat('2', filesep, num2str(N), '_img_gaussian_by_fourier', '.png'), 'png')
saveas(h_b1, strcat('2', filesep, num2str(N), '_img_block_by_fourier', '.png'), 'png')
% close all;