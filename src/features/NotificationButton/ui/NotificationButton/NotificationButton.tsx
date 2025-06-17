import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo, useCallback, useState } from "react";
import NotificationSVG from "resources/assets/icons/notification.svg";
import { MenuPopover } from "shared/MenuPopover/MenuPopover";
import { NotificationsList } from "entities/Notifications";
import { IconSVG } from "shared/IconSVG/IconSVG";
import { getUserAuthData } from "entities/User";
import { useSelector } from "react-redux";
import { Drawer } from "shared/Drawer/Drawer";
import { Button, ButtonTheme } from "shared/Button/Button";
import { BrowserView, MobileView } from "react-device-detect";
import cls from "./NotificationButton.module.scss";

interface INotificationButtonProps {
  className?: string;
}

export const NotificationButton = memo(({ className }: INotificationButtonProps) => {
  const [isOpen, setIsOpen] = useState(false);

  const authData = useSelector(getUserAuthData);

  const onDrawerOpen = useCallback(() => {
    setIsOpen(true);
  }, []);

  const onDrawerClose = useCallback(() => {
    setIsOpen(false);
  }, []);

  if (!authData) { return null; }

  return (
    <div>
      <BrowserView>
        <MenuPopover
          className={classes(cls.NotificationButton, {}, [className])}
          direction="bottomLeft"
          trigger={<IconSVG Svg={NotificationSVG} />}
        >
          <NotificationsList />
        </MenuPopover>
      </BrowserView>
      <MobileView>
        <Button theme={ButtonTheme.CLEAR} onClick={onDrawerOpen}><IconSVG Svg={NotificationSVG} /></Button>
        <Drawer isOpen={isOpen} onClose={onDrawerClose}>
          <NotificationsList />
        </Drawer>
      </MobileView>
    </div>

  );
});
