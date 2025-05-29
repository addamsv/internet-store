import { ImgHTMLAttributes, memo, ReactElement, useEffect, useLayoutEffect, useRef, useState } from "react";

interface ICImgProps extends ImgHTMLAttributes<HTMLImageElement> {
  className?: string;
  src?: string;
  alt?: string;
  fallback?: ReactElement;
  errorFallback?: ReactElement;
}

export const CImg = memo(({ className, src, alt = "image", fallback, errorFallback, ...otherProps }: ICImgProps) => {
  const [isLoading, setIsLoading] = useState<Boolean>(true);

  const [isError, setIsError] = useState<Boolean>(false);

  const isMounted = useRef(true);

  useEffect(() => {
    const image = new Image();

    image.src = src ?? "";

    image.onload = () => {
      if (isMounted.current) {
        setIsLoading(false);
      }
    };

    image.onerror = () => {
      if (isMounted.current) {
        setIsLoading(false);
        setIsError(true);
      }
    };

    return () => {
      isMounted.current = false;
    };
  }, [src]);

  if (isLoading && fallback) {
    return fallback;
  }

  if (isError && errorFallback) {
    return errorFallback;
  }

  return (
    <img className={className} src={src} alt={alt} {...otherProps} />
  );
});
