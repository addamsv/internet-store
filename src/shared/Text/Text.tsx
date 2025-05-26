import { classes, Mods } from "resources/lib/classNames/classes";
import { memo } from "react";
import { TextTheme, TextAlign, TextSize } from ".";
import cls from "./Text.module.scss";

type THeaderTag = "h1" | "h2" | "h3" | "h4" | "h5" | "h6";

interface ITextProps {
  className?: string;
  title?: string;
  text?: string;
  theme?: TextTheme;
  textAlign?: TextAlign;
  textSize?: TextSize;

  "data-testid"?: string;
}

export const Text = memo(({
  className,
  text,
  title,
  theme = TextTheme.PRIMARY,
  textAlign = TextAlign.CENTER,
  textSize = TextSize.M,
  "data-testid": dataTestId
}: ITextProps) => {
  const headerMapping: Record<TextSize, THeaderTag> = {
    [TextSize.XXS]: "h6",
    [TextSize.XS]: "h5",
    [TextSize.S]: "h4",
    [TextSize.M]: "h3",
    [TextSize.L]: "h2",
    [TextSize.XL]: "h1",
  };

  const Header = headerMapping[textSize];

  const mods: Mods = {
    [cls[theme]]: true,
    [cls[textAlign]]: true,
    [cls[textSize]]: true,
  };

  return (
    <>
      {title && (
      <Header
        data-testid={`${dataTestId}.Title`}
        className={classes(cls.title, mods, [className])}
      >
        {title}
      </Header>
      )}
      {text && (
      <p
        data-testid={`${dataTestId}.Text`}
        className={classes(cls.text, mods, [className])}
      >
        {text}
      </p>
      )}
    </>
  );
});
