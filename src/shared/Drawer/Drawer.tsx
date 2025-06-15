import { classes, Mods } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo, ReactNode } from "react";
import { useTheme } from "resources/store/ThemeProvider";
import { Portal } from "shared/Portal/Portal";
import { Overlay } from "shared/Overlay/Overlay";
import cls from "./Drawer.module.scss";

interface IDrawerProps {
  className?: string;
  children?: ReactNode;
  isOpen?: boolean;
  onClose?: () => void;
}

export const Drawer = memo((props: IDrawerProps) => {
  const { className, children, isOpen, onClose } = props;

  const { theme } = useTheme();

  const mods: Mods = {
    [cls.opened]: isOpen
  };

  return (
    <Portal>
      <div className={classes(cls.Drawer, {}, [className])}>
        <Overlay onClick={onClose} />
        <div className={cls.drawerContent}>
          {children}
        </div>
      </div>
    </Portal>
  );
});
