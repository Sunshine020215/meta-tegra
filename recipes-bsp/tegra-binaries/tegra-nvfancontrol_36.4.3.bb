DESCRIPTION = "NVIDIA Fan Control"
L4T_DEB_COPYRIGHT_MD5 = "c86b17102e0b23818b51e4460a55e4b5"

L4T_DEB_TRANSLATED_BPN = "nvidia-l4t-nvfancontrol"
DEPENDS = "tegra-nvpower"

require tegra-debian-libraries-common.inc

MAINSUM = "5ab541e2f32342da1dc74c83a309537b80ba3566f7b8050d554861ec5eebf0dc"

SRC_URI += "\
   file://nvfancontrol.init \
   file://nvfancontrol.service \
"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${B}/usr/sbin/nvfancontrol ${D}${sbindir}/
    install -d ${D}${sysconfdir}/init.d
    install -m 0644 ${B}/etc/nvpower/nvfancontrol/${NVFANCONTROL}.conf ${D}${sysconfdir}/nvfancontrol.conf
    install -m 0755 ${UNPACKDIR}/nvfancontrol.init ${D}${sysconfdir}/init.d/nvfancontrol
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/nvfancontrol.service ${D}${systemd_system_unitdir}/
}

inherit systemd update-rc.d

INITSCRIPT_NAME = "nvfancontrol"
INITSCRIPT_PARAMS = "defaults"
SYSTEMD_SERVICE:${PN} = "nvfancontrol.service"
RDEPENDS:${PN} = "bash tegra-nvpower tegra-nvpmodel"
PACKAGE_ARCH = "${MACHINE_ARCH}"
