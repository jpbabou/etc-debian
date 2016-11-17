TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd keyboard-setup hwclock.sh hostname.sh lvm2 checkroot.sh console-setup mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh networking urandom rpcbind nfs-common alsa-utils udev-finish checkfs.sh checkroot-bootclean.sh kmod bootmisc.sh kbd stop-bootlogd-single x11-common procps pppd-dns
INTERACTIVE = udev keyboard-setup checkroot.sh console-setup checkfs.sh kbd
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
keyboard-setup: mountkernfs.sh udev bootlogd
hwclock.sh: mountdevsubfs.sh bootlogd
hostname.sh: bootlogd
lvm2: bootlogd mountdevsubfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh bootlogd keyboard-setup
console-setup: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kbd
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking rpcbind nfs-common
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
rpcbind: networking mountall.sh mountall-bootclean.sh
nfs-common: rpcbind hwclock.sh
alsa-utils: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
checkfs.sh: checkroot.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh udev
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
stop-bootlogd-single: mountall.sh mountall-bootclean.sh udev keyboard-setup console-setup mountnfs.sh mountnfs-bootclean.sh networking hwclock.sh urandom mountkernfs.sh rpcbind nfs-common mountdevsubfs.sh checkroot.sh alsa-utils hostname.sh udev-finish bootlogd checkfs.sh checkroot-bootclean.sh kmod bootmisc.sh kbd x11-common procps lvm2 pppd-dns
x11-common: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev bootlogd
pppd-dns: mountall.sh mountall-bootclean.sh
