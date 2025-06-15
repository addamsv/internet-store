import { classes } from "resources/lib/classNames/classes";
import { memo } from "react";
import cls from "./Overlay.module.scss";

interface IOverlayProps {
  className?: string;
  onClick?: () => void;
}

export const Overlay = memo(({ className, onClick }: IOverlayProps) => {
  return (
    <div
      className={classes(cls.Overlay, {}, [className])}
      onClick={onClick}
    />
  );
});
