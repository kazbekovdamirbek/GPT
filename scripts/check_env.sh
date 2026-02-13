#!/usr/bin/env bash
set -euo pipefail

echo "== FoodLens Env Check =="

if command -v java >/dev/null 2>&1; then
  JAVA_VERSION=$(java -version 2>&1 | head -n1)
  echo "Java: $JAVA_VERSION"
else
  echo "Java: not found"
  exit 1
fi

if [[ -f local.properties ]]; then
  echo "local.properties: found"
  SDK_LINE=$(grep '^sdk.dir=' local.properties || true)
  if [[ -n "$SDK_LINE" ]]; then
    echo "sdk.dir: configured"
  else
    echo "sdk.dir: missing in local.properties"
  fi
else
  echo "local.properties: missing (copy from local.properties.example)"
fi

echo "Done"
