#!/usr/bin/env bash
set -euo pipefail

APP_NAME="notifications-app"
PORT="${SERVER_PORT:-8085}"
JWT_SECRET="${JWT_SECRET:-change_me_please_use_a_long_random_secret_32chars}"

if [ -f .env ]; then
  set -a
  source .env
  set +a
fi

if [ "${#JWT_SECRET}" -lt 32 ]; then
  echo "ERROR: JWT_SECRET must be at least 32 characters (HS256 requires 256-bit key)."
  exit 1
fi

echo "==> Building Docker image: ${APP_NAME}"
docker info >/dev/null 2>&1 || { echo "Docker is not running. Please start Docker and retry."; exit 1; }
docker build -t "${APP_NAME}" .

echo "==> Running container on port ${PORT}"
echo "    JWT_SECRET is set (length: ${#JWT_SECRET})"
docker run --rm \
  -p "${PORT}:${PORT}" \
  -e "JWT_SECRET=${JWT_SECRET}" \
  "${APP_NAME}"
