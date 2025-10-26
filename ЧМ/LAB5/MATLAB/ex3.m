close all;
clear all;
T = 2;  % Длина промежутка интегрирования по времени
N = 10000;  % Кол-во точек на промежутке T
a = -T/2;  % Нижний предел интегрирования по времени
b = T/2;
delta_t = (b - a)/(N - 1);
B = 1/2*(a/N + delta_t);  % Образ Фурье содержится внутри отрезка [-B, B]
delta_v = 1/N;


n = linspace(0, N - 1, N);
t_n = a + n * delta_t;

% parametres = "T_" + num2str(t) + "_dt_" + num2str(delta_t) + "_V_" + num2str(delta_v) + "_dv_"+ num2str(dv);
% path = "FM-LAB5" + filesep + parametres + filesep;
% if ~exist(path, 'dir')
%     mkdir(path);
% end
t = linspace(a, b, N);
nu = linspace(-B, B, 1/delta_v);
Pi_t_static = abs(t) <= 0.5;

Pi_t = abs(t_n) <= 0.5;
Pi_nu = sinc(nu);

m = linspace(-B, B, 1/delta_v);
c = (T/(N-1)) * exp(-1j * 2 * pi * (m/T) * a);

Pi_nu_numerical = fftshift(c.*fft(Pi_t)) / sqrt(N);
Pi_t_inversed = ifft(ifftshift(Pi_nu_numerical)./c) * sqrt(N);

p_fourier_numerical = figure;
plot(nu, Pi_nu_numerical, 'Color', 'b');
hold on;
plot(nu, Pi_nu, 'Color', 'r');
xlabel('Частота');
ylabel('Амплитуда');
real_Pi_num = real(Pi_nu_numerical);
% ylim([-(abs(min(real_Pi_num))+abs(min(real_Pi_num))*0.2), max(real_Pi_num)+max(real_Pi_num)*0.2])
xlim([max(-10, min(nu)), min(10, max(nu))])
legend('$\hat{\Pi}(\nu)$', '$\mathrm{sinc}(\pi\nu)$', 'Interpreter', 'latex', 'fontsize', 11)
grid on;
legend(BackgroundAlpha=.6);
% saveas(p_fourier_numerical, path + "fourier_numerical.png", "png")

p_t_inversed_numerical = figure;
plot(t, Pi_t_inversed, 'Color', 'b');
hold on;
plot(t_static, Pi_t_static, 'Color', 'black');
xlabel('t');
ylabel('\Pi(t)');
% ylim([min(Pi_t_inversed)+min(Pi_t_inversed)*0.2, max(Pi_t_inversed)+max(Pi_t_inversed)*0.2])
xlim([-3, 3]);
xticks(-5:1:5);
legend('$\mathcal{F}^{-1}\{\hat{\Pi}(\nu)\}$', '$\Pi(t)$', 'Interpreter', 'latex', 'fontsize', 11)
grid on;
legend(BackgroundAlpha=.6);
% saveas(p_t_inversed_numerical, path+"func_inversed_fourier.png", "png")