image = double(imread("im3_fourier_recovered.png"))./255;
fourier = ifft2(ifftshift(image));

% abs_fourier = abs(fourier);
% angle_fourier = angle(fourier);
% 
% abs_fourier_log = log(abs_fourier + 1);
% 
% abs_fourier_log_norm = abs_fourier_log./repmat(max(abs_fourier_log),size(abs_fourier_log,1),1);

imwrite(fourier, "im3_recovered.png")