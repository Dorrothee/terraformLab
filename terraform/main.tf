resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "subnet1" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.1.0/24"
  availability_zone = "eu-central-1"  # replace with your AWS region
}

resource "aws_subnet" "subnet2" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "10.0.2.0/24"
  availability_zone = "us-east-1"  # replace with your AWS region
}

data "aws_security_group" "existing" {
  id = "sg-022b28d8ed136c25a"
}

resource "aws_default_route_table" "main" {
  default_route_table_id = aws_vpc.main.default_route_table_id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }

  tags = {
    Name = "main"
  }
}

resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id
}




provider "aws" {
  region = "eu-central-1"
}

resource "aws_ecs_cluster" "my_cluster" {
  name = "my_cluster"
}

resource "aws_ecs_service" "my_service" {
  name            = "my_service"
  cluster         = aws_ecs_cluster.my_cluster.id
  task_definition = aws_ecs_task_definition.my_task_definition.arn
  desired_count   = 1
  launch_type     = "FARGATE"  // Or "EC2" depending on your preference

  network_configuration {
    subnets = ["subnet1", "subnet2"]
    security_groups = [aws_security_group.allow_all.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = "${aws_lb_target_group.my_target_group.arn}"
    container_name   = "my_container"
    container_port   = 8080
  }
}

resource "aws_ecs_task_definition" "my_task_definition" {

  family                   = "my_task_definition"
  network_mode             = "awsvpc"   //required if FARGATE launch type is selected
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"     // adjust to your needs
  memory                   = "512"     // adjust to your needs
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn

  container_definitions = jsonencode([{
    name   = "my_container"
    image  = "504306331314.dkr.ecr.eu-central-1.amazonaws.com/maven_app_repo:latest"
    cpu    = 0
    memory = 512

    portMappings = [{
      containerPort = 8080
      protocol      = "tcp"
    }]
  }])
}

resource "aws_iam_role" "ecs_execution_role" {
  name               = "ecs_execution_role"
  assume_role_policy = data.aws_iam_policy_document.ecs_assume_role.json
}

data "aws_iam_policy_document" "ecs_assume_role" {
  statement {
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ecs-tasks.amazonaws.com"]
    }
  }
}

resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id
}
