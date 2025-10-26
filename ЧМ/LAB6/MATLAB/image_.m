image = double(imread("3.png"))./255;
fourier = fftshift(fft2(image));

abs_fourier = abs(fourier);
angle_fourier = angle(fourier);

abs_fourier_log = log(abs_fourier + 1);

abs_fourier_log_norm = abs_fourier_log./max(abs_fourier_log(:), [], 'all');

imwrite(abs_fourier_log_norm, "im3_fourier.png")
recovered_log = double(imread("im3_fourier_recovered.png"))./255;


recovered_abs_log = recovered_log .* max(abs_fourier_log(:), [], 'all');
recovered_abs = exp(recovered_abs_log) - 1;
recovered_fourier = recovered_abs .* exp(1i * angle_fourier);
recovered_image = real(ifft2(ifftshift(recovered_fourier)));
imwrite(recovered_image, "im3_recovered.png");

