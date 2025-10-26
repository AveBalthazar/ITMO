close all;

t = 10;
T = [-t/2, t/2];
dt = 0.05;
a = min(T);
b = max(T);

v = 12;
V = [-v/2, v/2];
dv = 0.001;
v0 = min(V);
v1 = max(V);

parametres = "T_" + num2str(t) + "_dt_" + num2str(dt) + "_V_" + num2str(v) + "_dv_"+ num2str(dv);
path = "FM-LAB5" + filesep + parametres + filesep;
if ~exist(path, 'dir')
    mkdir(path);
end

t = linspace(a, b, (1/dt)*t);
nu = linspace(v0, v1, (1/dv)*v);
nu_static = linspace(v0, v1, 1000);
t_static = linspace(a, b, 1000);

Pi_t = abs(t) <= 0.5;
Pi_t_static = abs(t_static) <= 0.5;
Pi_nu = sinc(nu_static);

Pi_nu_numerical = zeros(size(nu));

for k = 1:length(nu)
    integrand = Pi_t .* exp(-1j * 2 * pi * nu(k) * t);
    Pi_nu_numerical(k) = trapz(t, integrand);
end

Pi_t_inversed = zeros(size(t));

for k = 1:length(t)
    integrand = Pi_nu_numerical .* exp(1j * 2 * pi * nu * t(k));
    Pi_t_inversed(k) = trapz(nu, integrand);
end

p_fourier_numerical = figure;
plot(nu, Pi_nu_numerical, 'Color', 'b');
hold on;
plot(nu_static, Pi_nu, 'Color', 'r');
xlabel('Частота');
ylabel('Амплитуда');
real_Pi_num = real(Pi_nu_numerical);
ylim([-(abs(min(real_Pi_num))+abs(min(real_Pi_num))*0.2), max(real_Pi_num)+max(real_Pi_num)*0.2])
xlim([max(-10, min(nu)), min(10, max(nu))])
legend('$\hat{\Pi}(\nu)$', '$\mathrm{sinc}(\pi\nu)$', 'Interpreter', 'latex', 'fontsize', 11)
grid on;
legend(BackgroundAlpha=.6);
saveas(p_fourier_numerical, path + "fourier_numerical.png", "png")

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
saveas(p_t_inversed_numerical, path+"func_inversed_fourier.png", "png")