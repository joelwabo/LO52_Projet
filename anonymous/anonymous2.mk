$(call inherit-product, device/linaro/pandaboard/pandaboard.mk)

DEVICE_PACKAGE_OVERLAYS:=\
    device/utbm/anonymous/overlay

PRODUCT_NAME:= anonymous
PRODUCT_DEVICE:= anonymous
PRODUCT_BRAND:= Android
PRODUCT_MODEL:= Android
