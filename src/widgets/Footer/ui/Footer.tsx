import { classes } from "resources/lib/classNames/classes";
import GitSVG from "resources/assets/icons/git.svg";
import NotificationSVG from "resources/assets/icons/notification.svg";
import BasketSVG from "resources/assets/icons/basket.svg";
import YouTubeSVG from "resources/assets/icons/youtube.svg";
import WiFiSVG from "resources/assets/icons/wifi.svg";
import LinkSVG from "resources/assets/icons/link.svg";
import InstagramSVG from "resources/assets/icons/instagram.svg";
import FacebookSVG from "resources/assets/icons/facebook.svg";
import { memo } from "react";
import { AppLink } from "shared/AppLink/AppLink";
import { HFlex } from "shared/Flex/HFlex";
import { IconSVG } from "shared/IconSVG/IconSVG";
import cls from "./Footer.module.scss";

interface FooterProps {
  className?: string;
}

export const Footer = memo(({ className }: FooterProps) => {
  return (
    <footer className={classes(cls.Footer, {}, [className])}>
      <HFlex justify="end" max gap="16" className={cls.footerWrapper}>
        {/* <img src={GitLogoLight} alt="" width={15} /> */}

        <AppLink target="blank" to="#">
          <IconSVG Svg={YouTubeSVG} />
        </AppLink>

        <AppLink target="blank" to="#">
          <IconSVG Svg={WiFiSVG} />
        </AppLink>

        <AppLink target="blank" to="#">
          <IconSVG Svg={InstagramSVG} />
        </AppLink>

        <AppLink target="blank" to="#">
          <IconSVG Svg={FacebookSVG} />
        </AppLink>

        <AppLink target="blank" to="#">
          <IconSVG Svg={BasketSVG} />
        </AppLink>

        <AppLink target="blank" to="https://github.com/addamsv">
          <IconSVG Svg={GitSVG} />
        </AppLink>

        <AppLink target="blank" to="https://www.linkedin.com/in/addamsv">
          <IconSVG Svg={LinkSVG} />
        </AppLink>
      </HFlex>
    </footer>
  );
});
